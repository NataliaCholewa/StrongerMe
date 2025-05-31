import { useEffect, useState } from "react";
import apiClient from "../services/apiClient";

const ExerciseDropdown = ({ onSelect }) => {
  const [exercisesByCategory, setExercisesByCategory] = useState({});
  const [expandedCategory, setExpandedCategory] = useState(null);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const fetchExercises = async () => {
      try {
        const res = await apiClient.get("/exercises");
        const grouped = {};

        res.data.forEach((ex) => {
          const cat = ex.category?.name || "Other";
          if (!grouped[cat]) grouped[cat] = [];
          grouped[cat].push(ex);
        });

        setExercisesByCategory(grouped);
      } catch (err) {
        console.error("Failed to load exercises", err);
      }
    };
    fetchExercises();
  }, []);

  const toggle = (cat) => {
    setExpandedCategory((prev) => (prev === cat ? null : cat));
  };

  const filteredExercises = (list) =>
    list.filter((ex) =>
      ex.name.toLowerCase().includes(search.toLowerCase())
    );

  return (
    <div>
      <input
        type="text"
        placeholder="Search exercises..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      {Object.entries(exercisesByCategory).map(([category, list]) => (
        <div key={category}>
          <button onClick={() => toggle(category)}>{category}</button>
          {expandedCategory === category && (
            <ul>
              {filteredExercises(list).map((ex) => (
                <li key={ex.id}>
                  {ex.name} <button onClick={() => onSelect(ex)}>Add</button>
                </li>
              ))}
            </ul>
          )}
        </div>
      ))}
    </div>
  );
};

export default ExerciseDropdown;

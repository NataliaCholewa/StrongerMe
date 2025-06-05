import { useEffect, useState } from "react";
import apiClient from "../services/apiClient";
import ExerciseItem from "./ExerciseItem";

const ExerciseDropdown = ({ onSelect }) => {
  const [exercisesByCategory, setExercisesByCategory] = useState({});
  const [expandedCategory, setExpandedCategory] = useState(null);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const fetchExercises = async () => {
      try {
        const res = await apiClient.get("/exercises");

        if (!Array.isArray(res.data)) {
          throw new Error("Expected an array from /exercises");
        }

        const grouped = {};
        res.data.forEach((ex) => {
          if (!ex.categoryName) return;
          const cat = ex.categoryName;
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
        className="exercise-search"
      />

      <div className="category-grid">
        {Object.entries(exercisesByCategory).map(([category, list]) => (
          <div key={category}>
            <button
              onClick={() => toggle(category)}
              className="category-button"
            >
              {category}
            </button>

            {expandedCategory === category && (
              <div className="flex flex-col gap-2 ml-4 mt-2">
                {filteredExercises(list).map((ex) => (
                  <ExerciseItem key={ex.id} exercise={ex} onSelect={onSelect} />
                ))}
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default ExerciseDropdown;

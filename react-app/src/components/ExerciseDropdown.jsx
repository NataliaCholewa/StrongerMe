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

  <pre>{JSON.stringify(exercisesByCategory, null, 2)}</pre>


  return (
    <div>
      <input
        type="text"
        placeholder="Search exercises..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="mb-4 p-2 border rounded w-full"
      />

      {Object.entries(exercisesByCategory).map(([category, list]) => (
        <div key={category} className="mb-4">
          <button
            onClick={() => toggle(category)}
            className="font-bold text-left text-lg mb-2"
          >
            {category}
          </button>

          {expandedCategory === category && (
            <div className="flex flex-col gap-2 ml-4">
              {filteredExercises(list).map((ex) => (
                <ExerciseItem key={ex.id} exercise={ex} onSelect={onSelect} />
              ))}
            </div>
          )}
        </div>
      ))}
    </div>
  );
};

export default ExerciseDropdown;

import { useState } from "react";
import { Info } from "lucide-react";

const ExerciseItem = ({ exercise, onSelect }) => {
  const [showInfo, setShowInfo] = useState(false);

  const handleToggleInfo = () => setShowInfo(prev => !prev);
  const imageUrl = exercise.imageUrl?.startsWith("http")
    ? exercise.imageUrl
    : `http://localhost:8080${exercise.imageUrl}`;

  return (
    <div className="flex flex-col gap-2 border p-3 rounded shadow-sm bg-white">
      <div className="flex items-center justify-between">
        <span className="font-medium">{exercise.name}</span>

        <div className="flex items-center gap-2">
          <button
            onClick={handleToggleInfo}
            className="text-blue-500 hover:text-blue-700"
            title="Show info"
          >
            <Info size={18} />
          </button>
          <button
            onClick={() => onSelect(exercise)}
            className="bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600"
          >
            Add
          </button>
        </div>
      </div>

      {showInfo && (
        <div className="border p-2 bg-gray-50 rounded text-sm">
          <p className="mb-2">{exercise.description || "No description."}</p>
          <img
            src={imageUrl}
            alt={exercise.name}
            className="max-h-48 object-contain mx-auto"
            onError={(e) => {
              e.target.style.display = "none";
              console.warn("Image not found:", imageUrl);
            }}
          />
        </div>
      )}
    </div>
  );
};

export default ExerciseItem;

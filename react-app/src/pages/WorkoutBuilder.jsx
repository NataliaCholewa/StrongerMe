import { useState } from "react";
import ExerciseDropdown from "../components/ExerciseDropdown";

const WorkoutBuilder = () => {
  const [selectedExercises, setSelectedExercises] = useState([]);

  const handleExerciseSelect = (exercise) => {
    if (!selectedExercises.find(e => e.id === exercise.id)) {
      setSelectedExercises(prev => [...prev, exercise]);
    }
  };

  const handleRemove = (id) => {
    setSelectedExercises(prev => prev.filter(e => e.id !== id));
  };

  const handleSaveWorkout = () => {
    console.log("Saving workout with exercises:", selectedExercises);
  };

  return (
    <div>
      <h2>Create Workout</h2>

      <ExerciseDropdown onSelect={handleExerciseSelect} />

      <h3>Selected Exercises</h3>
      {selectedExercises.length === 0 ? (
        <p>No exercises added yet.</p>
      ) : (
        <ul>
          {selectedExercises.map((exercise) => (
            <li key={exercise.id}>
              {exercise.name}
              <button onClick={() => handleRemove(exercise.id)}>Remove</button>
            </li>
          ))}
        </ul>
      )}

      <button onClick={handleSaveWorkout}>Save Workout</button>
    </div>
  );
};

export default WorkoutBuilder;

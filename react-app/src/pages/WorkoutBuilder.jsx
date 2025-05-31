import { useState } from "react";
import ExerciseDropdown from "../components/ExerciseDropdown";
import apiClient from "../services/apiClient";
import { useNavigate } from "react-router-dom";

const WorkoutBuilder = () => {
  const [selectedExercises, setSelectedExercises] = useState([]);
  const [workoutInfo, setWorkoutInfo] = useState({ name: "", description: "" });
  
  const navigate = useNavigate();

  const handleExerciseSelect = (exercise) => {
    const exists = selectedExercises.find(e => e.id === exercise.id);
    if (!exists) {
      setSelectedExercises(prev => [
        ...prev,
        { ...exercise, sets: "", reps: "", weight: "" }
      ]);
    }
  };

  const handleExerciseChange = (id, field, value) => {
    setSelectedExercises(prev =>
      prev.map(ex =>
        ex.id === id ? { ...ex, [field]: value } : ex
      )
    );
  };

  const handleRemove = (id) => {
    setSelectedExercises(prev => prev.filter(e => e.id !== id));
  };

  const handleWorkoutInfoChange = (e) => {
    const { name, value } = e.target;
    setWorkoutInfo(prev => ({ ...prev, [name]: value }));
  };

  const handleSaveWorkout = async () => {
    try {
      const workoutPayload = {
        name: workoutInfo.name,
        description: workoutInfo.description,
        exercises: selectedExercises.map(e => ({
          exerciseId: e.id,
          sets: Number(e.sets),
          reps: Number(e.reps),
          weight: Number(e.weight)
        }))
      };

      await apiClient.post("/workouts", workoutPayload);
      alert("Workout saved!");
      navigate("/workouts");
    } catch (err) {
      console.error("Failed to save workout", err);
      alert("Error saving workout.");
    }
  };

  return (
    <div>
      <h2>Create Workout</h2>

      <div>
        <input
          name="name"
          placeholder="Workout name"
          value={workoutInfo.name}
          onChange={handleWorkoutInfoChange}
          required
        />
        <textarea
          name="description"
          placeholder="Workout description"
          value={workoutInfo.description}
          onChange={handleWorkoutInfoChange}
        />
      </div>

      <ExerciseDropdown onSelect={handleExerciseSelect} />

      <h3>Selected Exercises</h3>
      {selectedExercises.length === 0 ? (
        <p>No exercises added yet.</p>
      ) : (
        <ul>
          {selectedExercises.map((exercise) => (
            <li key={exercise.id}>
              <strong>{exercise.name}</strong>
              <div>
                <input
                  type="number"
                  placeholder="Sets"
                  value={exercise.sets}
                  onChange={(e) =>
                    handleExerciseChange(exercise.id, "sets", e.target.value)
                  }
                />
                <input
                  type="number"
                  placeholder="Reps"
                  value={exercise.reps}
                  onChange={(e) =>
                    handleExerciseChange(exercise.id, "reps", e.target.value)
                  }
                />
                <input
                  type="number"
                  placeholder="Weight (kg)"
                  value={exercise.weight}
                  onChange={(e) =>
                    handleExerciseChange(exercise.id, "weight", e.target.value)
                  }
                />
                <button onClick={() => handleRemove(exercise.id)}>Remove</button>
              </div>
            </li>
          ))}
        </ul>
      )}

      <button onClick={handleSaveWorkout} disabled={selectedExercises.length === 0}>
        Save Workout
      </button>
    </div>
  );
};

export default WorkoutBuilder;

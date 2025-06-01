import { useState } from "react";
import apiClient from "../services/apiClient";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

const EditWorkoutPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({ name: "", description: "", workoutExercises: [], });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchWorkout = async () => {
      try {
        const res = await apiClient.get(`/workouts/${id}`);
        setFormData({
          name: res.data.name,
          description: res.data.description,
          workoutExercises: res.data.workoutExercises,
        });
      } catch (err) {
        console.error("Failed to load workout", err);
      } finally {
        setLoading(false);
      }
    };

    fetchWorkout();
  }, [id]);

  const handleExerciseChange = (index, field, value) => {
  setFormData((prev) => {
    const updatedExercises = [...prev.workoutExercises];
    updatedExercises[index] = {
      ...updatedExercises[index],
      [field]: value,
    };
    return {
      ...prev,
      workoutExercises: updatedExercises,
    };
  });
};


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await apiClient.put(`/workouts/${id}`, formData);
      alert("Workout updated successfully!");
      navigate(`/workouts/${id}`);
    } catch (err) {
        console.error("Update failed", err);
        alert("Something went wrong while updating workout.");
    }
  };

  if (!workout) return <p>Loading...</p>;

   return (
  <div>
    <h2>Edit Workout</h2>

    <form onSubmit={handleSubmit}>
      <input
        name="name"
        value={formData.name}              
        onChange={handleChange}
        placeholder="Workout name"
        required
      />
      <textarea
        name="description"
        value={formData.description}
        onChange={handleChange}
        placeholder="Workout description"
      />

      <h3>Edit Exercises</h3>
      {formData.workoutExercises?.length > 0 ? (
        formData.workoutExercises.map((ex, index) => (
          <div key={ex.id}>
            <strong>{ex.exercise.name}</strong>
            <input
              type="number"
              value={ex.sets}
              onChange={(e) =>
                handleExerciseChange(index, "sets", e.target.value)
              }
              placeholder="Sets"
            />
            <input
              type="number"
              value={ex.reps}
              onChange={(e) =>
                handleExerciseChange(index, "reps", e.target.value)
              }
              placeholder="Reps"
            />
            <input
              type="number"
              value={ex.weight}
              onChange={(e) =>
                handleExerciseChange(index, "weight", e.target.value)
              }
              placeholder="Weight (kg)"
            />
          </div>
        ))
      ) : (
        <p>No exercises to edit.</p>
      )}

      <button type="submit">Save changes</button>
    </form>
  </div>
);
};

export default EditWorkoutPage;
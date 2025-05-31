import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import apiClient from "../services/apiClient";

const WorkoutDetailsPage = () => {
  const { id } = useParams(); 
  const [workout, setWorkout] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchWorkout = async () => {
      try {
        const res = await apiClient.get(`/workouts/${id}`);
        setWorkout(res.data);
      } catch (err) {
        console.error("Failed to load workout", err);
      } finally {
        setLoading(false);
      }
    };

    fetchWorkout();
  }, [id]);

  if (loading) return <p>Loading workout...</p>;
  if (!workout) return <p>Workout not found.</p>;

  return (
    <div>
      <h2>{workout.name}</h2>
      <p>{workout.description}</p>
      <small>{new Date(workout.performedAt).toLocaleString()}</small>

      <h3>Exercises</h3>
      {workout.workoutExercises && workout.workoutExercises.length > 0 ? (
        <ul>
          {workout.workoutExercises.map((ex) => (
            <li key={ex.id}>
              <strong>{ex.exercise.name}</strong> – {ex.sets} sets × {ex.reps} reps @ {ex.weight} kg
            </li>
          ))}
        </ul>
      ) : (
        <p>No exercises in this workout.</p>
      )}
    </div>
  );
};

export default WorkoutDetailsPage;

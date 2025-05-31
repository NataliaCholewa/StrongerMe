import { useEffect, useState } from "react";
import apiClient from "../services/apiClient";
import { Link } from "react-router-dom";



const WorkoutsPage = () => {
  const [workouts, setWorkouts] = useState([]);
  const [loading, setLoading] = useState(true);


  useEffect(() => {
    const fetchWorkouts = async () => {
      try {
        const res = await apiClient.get("/workouts/me");
        setWorkouts(res.data);
      } catch (err) {
        console.error("Failed to fetch workouts:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchWorkouts();
  }, []);

  if (loading) return <p>Loading workouts...</p>;

  return (
    <div>
        <Link to="/create-workout">
            <button>Add Workout</button>
        </Link>
      <h2>My Workouts</h2>
      {workouts.length === 0 ? (
        <p>No workouts found.</p>
      ) : (
        <ul>
          {workouts.map((workout) => (
            <li key={workout.id}>
              <strong>{workout.name}</strong> – {workout.description}<br />
              <small>{new Date(workout.performedAt).toLocaleString()}</small>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default WorkoutsPage;

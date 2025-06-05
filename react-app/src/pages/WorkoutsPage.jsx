import { useEffect, useState } from "react";
import apiClient from "../services/apiClient";
import { Link } from "react-router-dom";
import "../App.css"; 

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

  const handleDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete this workout?");
    if (!confirmed) return;

    try {
      await apiClient.delete(`/workouts/${id}`);
      setWorkouts((prev) => prev.filter((w) => w.id !== id));
      alert("Workout deleted.");
    } catch (err) {
      console.error("Delete failed", err);
      alert("Failed to delete workout.");
    }
  };

  return (
    <div className="workouts-container">
      <div className="header-row">
        <h2>My Workouts</h2>
<div className="add-workout-button">
  <Link to="/create-workout">
    <button className="primary-btn">Add Workout</button>
  </Link>
</div>
</div>

      {loading ? (
        <p>Loading workouts...</p>
      ) : workouts.length === 0 ? (
        <p>No workouts found.</p>
      ) : (
        <div className="workout-grid">
          {workouts.map((workout) => (
            <div key={workout.id} className="workout-card">
              <h3>{workout.name}</h3>
              <p className="workout-desc">{workout.description}</p>
              <p className="workout-date">
                {new Date(workout.performedAt).toLocaleString()}
              </p>
              <div className="workout-actions">
                <button onClick={() => handleDelete(workout.id)}>Delete workout</button>
                <Link to={`/workouts/${workout.id}`} className="details-link">
                  View details
                </Link>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default WorkoutsPage;

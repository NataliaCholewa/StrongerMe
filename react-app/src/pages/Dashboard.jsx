import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";
import apiClient from "../services/apiClient";
import "../App.css"; // upewnij się że masz style summary-card itd.

const Dashboard = () => {
  const { user } = useAuth();
  const [summary, setSummary] = useState(null);

  useEffect(() => {
    apiClient
      .get("/dashboard")
      .then((res) => setSummary(res.data))
      .catch((err) => console.error("Failed to load dashboard summary", err));
  }, []);

  return (
    <div className="dashboard-container">
      <h2>Welcome back, {user?.firstName}.</h2>
      <p>Let's workout! 💪</p>

      <Link to="/create-workout">
        <button className="primary-btn">Add Workout</button>
      </Link>

      {user?.role === "ADMIN" && (
        <Link to="/admin/exercises">
          <button className="secondary-btn">Add Exercise</button>
        </Link>
      )}

      <h3>Summary</h3>
      {summary ? (
        <div className="summary-grid">
          <div className="summary-card">
            <p>workouts done:</p>
            <strong>{summary.totalWorkouts}</strong>
          </div>
          <div className="summary-card">
            <p>volume lifted:</p>
            <strong>{summary.totalVolume} kg</strong>
          </div>
          <div className="summary-card">
            <p>weekly aim:</p>
            <strong>
              {summary.currentWeekCount}/{summary.weeklyGoal}
            </strong>
          </div>
        </div>
      ) : (
        <p>Loading summary...</p>
      )}
    </div>
  );
};

export default Dashboard;

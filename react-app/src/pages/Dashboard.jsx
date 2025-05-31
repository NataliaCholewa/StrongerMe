import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";



const Dashboard = () => {
  const { user } = useAuth();

  return (
    <div>
      <h1>Dashboard</h1>
      {user && <h2>Welcome back, {user.firstName}! 💪</h2>}
      <Link to="/create-workout">
        <button>Create Workout</button>
      </Link>
      {user?.role === "ADMIN" && (
      <Link to="/create-exercise">
        <button>Create Exercise</button>
      </Link>
)}
    </div>

  );
};

export default Dashboard;

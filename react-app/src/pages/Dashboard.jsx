import { useAuth } from "../context/AuthContext";

const Dashboard = () => {
  const { user } = useAuth();

  return (
    <div>
      <h1>Dashboard</h1>
      {user && <h2>Welcome back, {user.firstName}! 💪</h2>}
    </div>
  );
};

export default Dashboard;

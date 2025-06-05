import { Link, Outlet } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../App.css";

const Layout = () => {
  const { user, logout } = useAuth();

  return (
    <div className="layout">
      <aside className="sidebar">
        <div className="logo">🏋️ <strong>StrongerMe</strong></div>
        <ul>
          <li><Link to="/dashboard">Dashboard</Link></li>
          <li><Link to="/profile">Profile</Link></li>
          <li><Link to="/workouts">Workouts</Link></li>
          {user?.role === "ADMIN" && (
            <li><Link to="/admin/exercises">Admin Panel</Link></li>
          )}
          <li><button className="logout-btn" onClick={logout}>Logout</button></li>
        </ul>
      </aside>

      <main className="main">
        <Outlet /> {}
      </main>
    </div>
  );
};

export default Layout;

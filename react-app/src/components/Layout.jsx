import { Link, Outlet } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { FaUser, FaPlus, FaSignOutAlt, FaDumbbell, FaHome } from "react-icons/fa";
import "../App.css";

const Layout = () => {
  const { user, logout } = useAuth();

  return (
    <div className="layout">
      <aside className="sidebar">
        <div className="logo">🏋️ <strong>StrongerMe</strong></div>

        <nav className="nav-links">
          <ul>
            <li><Link to="/dashboard"><FaHome /> Dashboard</Link></li>
            <li><Link to="/profile"><FaUser /> Profile</Link></li>
            <li><Link to="/workouts"><FaDumbbell /> Workouts</Link></li>
            {user?.role === "ADMIN" && (
              <li><Link to="/admin/exercises"><FaPlus /> Admin Panel</Link></li>
            )}
          </ul>
        </nav>

        <button className="logout-btn" onClick={logout}>
          <FaSignOutAlt /> Logout
        </button>
      </aside>

      <main className="main">
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;

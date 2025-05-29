import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Navbar = () => {
  const { isAuthenticated, user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); 
    navigate("/login");
  };

  return (
  <nav>
  <ul>
    <li><Link to="/">Home</Link></li>
    {isAuthenticated && user ? (
      <>
        <li><Link to="/dashboard">Dashboard</Link></li>
        <li><Link to="/profile">Profile</Link></li>
        <li><button onClick={handleLogout}>Logout</button></li>
      </>
    ) : (
      <>
        <li><Link to="/login">Log in</Link></li>
        <li><Link to="/register">Register</Link></li>
      </>
    )}
  </ul>
</nav>

);

};

export default Navbar;

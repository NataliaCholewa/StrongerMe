import { Link } from "react-router-dom";
import "../App.css";

const HomePage = () => {
  return (
    <div className="home-page">
      <div className="home-content">
        <h1>StrongerMe</h1>
        <p className="subtitle">
          Our app was designed to help you track your workouts, monitor progress and plan your fitness goals.
        </p>
        <div className="home-buttons">
          <Link to="/login" className="home-btn">Log in</Link>
          <Link to="/register" className="home-btn outline">Register</Link>
        </div>
      </div>
    </div>
  );
};

export default HomePage;

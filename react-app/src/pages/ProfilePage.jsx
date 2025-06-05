import { useAuth } from "../context/AuthContext";
import { FaUserCircle } from "react-icons/fa";
import "../App.css"; 

const ProfilePage = () => {
  const { user } = useAuth();

  if (!user) return <p>Loading...</p>;

  const joinedDate = new Date(user.createdAt).toLocaleDateString();
  const age = user.birthDate
    ? Math.floor((new Date() - new Date(user.birthDate)) / (365.25 * 24 * 60 * 60 * 1000))
    : "—";

  return (
    <div className="profile-wrapper">
      <div className="profile-avatar">
        <div className="avatar-icon">
  <FaUserCircle size={100} color="#888" />
</div>
        <h2>{user.firstName} {user.lastName}</h2>
        <p className="joined-date">Joined {joinedDate}</p>
      </div>

      <div className="profile-details">
        <h3>Personal Details</h3>
        <ul>
          <li><strong>Gender:</strong> {user.gender || "—"}</li>
          <li><strong>Age:</strong> {age}</li>
          <li><strong>Weight:</strong> {user.weight} kg</li>
          <li><strong>Height:</strong> {user.height} cm</li>
        </ul>
      </div>
    </div>
  );
};

export default ProfilePage;

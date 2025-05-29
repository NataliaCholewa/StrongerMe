import { useAuth } from "../context/AuthContext";
import apiClient from "../services/apiClient";
import { useState } from "react";

const ProfilePage = () => {
  const { user, token } = useAuth();

  const [formData, setFormData] = useState({
    firstName: user?.firstName || "",
    lastName: user?.lastName || "",
    birthDate: user?.birthDate || "",
    height: user?.height || "",
    weight: user?.weight || "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await apiClient.put("/users/me", formData, {
        headers: { Authorization: `Bearer ${token}` },
      });
      alert("Profile updated successfully!");
    } catch (err) {
      alert("Something went wrong while updating profile.");
      console.error(err);
    }
  };

  if (!user) return <p>Loading...</p>;

  return (
    <div>
      <h2>User Profile</h2>
      <p><strong>Email:</strong> {user.email}</p>

      <form onSubmit={handleSubmit}>
        <input
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          placeholder="First name"
        />
        <input
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          placeholder="Last name"
        />
        <input
          name="birthDate"
          type="date"
          value={formData.birthDate}
          onChange={handleChange}
        />
        <input
          name="height"
          type="number"
          value={formData.height}
          onChange={handleChange}
          placeholder="Height (cm)"
        />
        <input
          name="weight"
          type="number"
          value={formData.weight}
          onChange={handleChange}
          placeholder="Weight (kg)"
        />
        <button type="submit">Save changes</button>
      </form>
    </div>
  );
};

export default ProfilePage;

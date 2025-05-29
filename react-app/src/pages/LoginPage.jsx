import { useState } from "react";
import apiClient from "../services/apiClient";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null); 
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setError(null); 

      const res = await apiClient.post("/auth/login", { email, password });

      login(res.data.token);

      navigate("/dashboard");
    } catch (err) {
      setError("Nieprawidłowy email lub hasło.");
      console.error("Login error:", err.response?.data || err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Logowanie</h2>

      <input
        type="email"
        name="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />

      <input
        type="password"
        name="password"
        placeholder="Hasło"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />

      <button type="submit">Zaloguj</button>

      {error && <p style={{ color: "red" }}>{error}</p>}
    </form>
  );
};

export default LoginPage;

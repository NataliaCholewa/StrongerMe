import { useState } from "react";
import apiClient from "../services/apiClient";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault(); 
    try {
      const res = await apiClient.post("/auth/login", { email, password });
      login(res.data.token); 
      navigate("/dashboard"); 
    } catch (err) {
      alert("Błąd logowania");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Logowanie</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Hasło"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button type="submit">Zaloguj</button>
    </form>
  );
};

export default LoginPage;

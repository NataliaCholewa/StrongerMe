import { useState } from "react";
import apiClient from "../services/apiClient";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    birthDate: "",
    gender: "",
    weight: "",
    height: "",
  });

  const handleChange = (e) => {            
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {   
    e.preventDefault();
    try {
      await apiClient.post("/auth/register", formData);
      alert("Rejestracja zakończona sukcesem!");
      navigate("/login");
    } catch (err) {
  console.error("Błąd rejestracji:", err.response?.data || err.message);
  alert("Wystąpił błąd podczas rejestracji.");
}
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Rejestracja</h2>
      <input name="email" placeholder="Email" onChange={handleChange} />
      <input name="password" type="password" placeholder="Hasło" onChange={handleChange} />
      <input name="firstName" placeholder="Imię" onChange={handleChange} />
      <input name="lastName" placeholder="Nazwisko" onChange={handleChange} />
      <input name="birthDate" type="date" placeholder="Data urodzenia" onChange={handleChange} />
      <select name="gender" onChange={handleChange}>
        <option value="">Wybierz płeć</option>
        <option value="MALE">Mężczyzna</option>
        <option value="FEMALE">Kobieta</option>
      </select>
      <input name="weight" type="number" placeholder="Waga (kg)" onChange={handleChange} />
      <input name="height" type="number" placeholder="Wzrost (cm)" onChange={handleChange} />
      <button type="submit">Zarejestruj się</button>
    </form>
  );
};

export default RegisterPage;

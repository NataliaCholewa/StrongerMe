import { useState } from "react";
import apiClient from "../services/apiClient";
import { useNavigate } from "react-router-dom";

const CreateExercisePage = () => {
  const [form, setForm] = useState({
    name: "",
    description: "",
    imageUrl: "",
    isUnilateral: false,
    categoryId: "",
  });
  
  const [categories, setCategories] = useState([]);

  useEffect(() => {
  const fetchCategories = async () => {
    try {
      const res = await apiClient.get("/exercise-categories");
      setCategories(res.data);
    } catch (err) {
      console.error("Failed to fetch categories", err);
    }
  };
  fetchCategories();
}, []);

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await apiClient.post("/exercises", form);
      alert("Exercise created!");
      navigate("/"); 
    } catch (err) {
      alert("Error creating exercise.");
      console.error(err);
    }
  };

  return (
    <div>
      <h2>Create Exercise</h2>
      <form onSubmit={handleSubmit}>
        <select
            name="categoryId"
            value={form.categoryId}
            onChange={handleChange}
            required
        >
        <option value="">-- Select category --</option>
            {categories.map((cat) => (
        <option key={cat.id} value={cat.id}>
            {cat.name}
        </option>
  ))}
</select>
        <input
          name="name"
          placeholder="Exercise name"
          value={form.name}
          onChange={handleChange}
          required
        />
        <textarea
          name="description"
          placeholder="Description"
          value={form.description}
          onChange={handleChange}
        />
        <input
          name="imageUrl"
          placeholder="Image URL"
          value={form.imageUrl}
          onChange={handleChange}
        />
        <label>
          <input
            type="checkbox"
            name="isUnilateral"
            checked={form.isUnilateral}
            onChange={handleChange}
          />
          Is unilateral
        </label>
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default CreateExercisePage;

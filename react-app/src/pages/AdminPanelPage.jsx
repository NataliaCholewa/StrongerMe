import { useState, useEffect } from "react";
import apiClient from "../services/apiClient";

const AdminPanelPage = () => {
  const [form, setForm] = useState({ name: "", description: "", imageUrl: "", isUnilateral: false, categoryId: "" });
  const [imageFile, setImageFile] = useState(null);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      const res = await apiClient.get("/exercise-categories");
      setCategories(res.data);
    };
    fetchCategories();
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({ ...prev, [name]: type === "checkbox" ? checked : value }));
  };

    const handleFileChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const uploadImage = async () => {
    const data = new FormData();
    data.append("file", imageFile);

    const res = await apiClient.post("/uploads/exercise-image", data, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    return res.data.imageUrl;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      let imageUrl = "";

      if (imageFile) {
        imageUrl = await uploadImage();
      }

      const payload = {
        name: form.name,
        description: form.description,
        imageUrl: form.imageUrl,
        unilateral: form.unilateral,
        categoryId: form.categoryId, 
      };

      await apiClient.post("/exercises", payload);
      alert("Exercise created!");
      setForm({ name: "", description: "", imageUrl: "", isUnilateral: false, categoryId: "" });
      setImageFile(null);
    } catch (err) {
      console.error("Error creating exercise", err);
      alert("Failed to add exercise.");
    }
  };

  return (
    <div><h2>Create Exercise</h2>
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
          type="file"
          accept="image/*"
          onChange={handleFileChange}
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

export default AdminPanelPage;

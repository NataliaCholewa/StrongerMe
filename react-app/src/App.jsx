import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import Dashboard from "./pages/Dashboard";
import WorkoutsPage from "./pages/WorkoutsPage";
import ProtectedRoute from "./components/ProtectedRoute";
import WorkoutBuilder from "./pages/WorkoutBuilder";
import WorkoutDetailsPage from "./pages/WorkoutDetailsPage";
import EditWorkoutPage from "./pages/EditWorkoutPage";
import AdminPanelPage from "./pages/AdminPanelPage";
import HomePage from "./pages/HomePage";
import Layout from "./components/Layout";
import "./App.css";



function App() {
  return (
    <>
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      <Route element={<ProtectedRoute><Layout /></ProtectedRoute>}>
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/profile" element={<ProfilePage />} />
      <Route path="/workouts" element={<WorkoutsPage />} />
      <Route path="/create-workout" element={<WorkoutBuilder />} />
      <Route path="/workouts/:id" element={<WorkoutDetailsPage />} />
      <Route path="/workouts/:id/edit" element={<EditWorkoutPage />} />
      <Route path="/admin/exercises" element={<AdminPanelPage />} />
</Route>
      </Routes>
    </>
  );
}
export default App;

import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import Dashboard from "./pages/Dashboard";
import WorkoutsPage from "./pages/WorkoutsPage";
import ProtectedRoute from "./components/ProtectedRoute";
import Navbar from "./components/Navbar";
import WorkoutBuilder from "./pages/WorkoutBuilder";
import WorkoutDetailsPage from "./pages/WorkoutDetailsPage";
import EditWorkoutPage from "./pages/EditWorkoutPage";
import AdminPanelPage from "./pages/AdminPanelPage";




function App() {
  return (
    <>
    <Navbar />
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/dashboard" element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        }
      />
      <Route path="/profile" element={<ProfilePage />} />
      <Route path="/workouts" element={<WorkoutsPage />} />
      <Route path="/create-workout" element={<WorkoutBuilder />} />
      <Route path="/workouts/:id" element={<WorkoutDetailsPage />} />
      <Route path="/workouts/:id/edit" element={<EditWorkoutPage />} />
      <Route path="/admin/exercises" element={
    <ProtectedRoute adminOnly={true}>
      <AdminPanelPage />
    </ProtectedRoute>
  }
/>

      
    </Routes>

    </>
  );
}

export default App;

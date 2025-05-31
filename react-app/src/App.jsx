import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import Dashboard from "./pages/Dashboard";
import WorkoutsPage from "./pages/WorkoutsPage";
import ProtectedRoute from "./components/ProtectedRoute";
import Navbar from "./components/Navbar";
import WorkoutBuilder from "./pages/WorkoutBuilder";
import CreateExercisePage from "./pages/CreateExercisePage";


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
      <Route path="/create-exercise" element={<CreateExercisePage />} />
    </Routes>

    </>
  );
}

export default App;

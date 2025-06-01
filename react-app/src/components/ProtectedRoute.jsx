import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const ProtectedRoute = ({ children, adminOnly = false }) => {
  const { isAuthenticated, user } = useAuth();

  if (!isAuthenticated) return <Navigate to="/login" />;
  if (adminOnly && user?.role !== "ADMIN") return <Navigate to="/" />;

  return children;
};


export default ProtectedRoute;

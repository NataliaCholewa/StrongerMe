import { createContext, useContext, useEffect, useState } from "react";
import apiClient from "../services/apiClient";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token") || null);
  const [user, setUser] = useState(null);

  const isAuthenticated = !!token;

  const login = async (newToken) => {
    localStorage.setItem("token", newToken);
    setToken(newToken);

    try {
      const res = await apiClient.get("/users/me", {
        headers: {
          Authorization: `Bearer ${newToken}`,
        },
      });
      setUser(res.data);
    } catch (err) {
      console.error("Failed to fetch user data during login:", err);
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setUser(null);
  };

  useEffect(() => {
    const fetchUser = async () => {
      if (token && !user) {
        try {
          const res = await apiClient.get("/users/me", {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          setUser(res.data);
        } catch (err) {
          console.error("Auto-fetch user failed:", err);
          logout(); 
        }
      }
    };

    fetchUser();
  }, [token]);

  return (
    <AuthContext.Provider value={{ token, login, logout, isAuthenticated, user }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

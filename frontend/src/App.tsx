import React, { useEffect, useState } from "react";
import LoginPage from "./LoginPage";
import Dashboard from "./Dashboard";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import { isExpired } from "react-jwt";

const App = () => {
  const { isAuthenticated } = useAuth0();
  console.log(isAuthenticated)
  const [token, setToken] = useState<string>("");
  useEffect(() => {
    const token: string = localStorage.getItem("token")!;
    setToken(token);
  }, [token, isAuthenticated]);
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/dashboard"
          element={
            isAuthenticated || !isExpired(token) ? (
              <Dashboard />
            ) : (
              <Navigate to="/" />
            )
          }
        />
        <Route path="/" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;

import React, { useState } from "react";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [userDetails, setUserDetails] = useState(null);
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8081/api/auth/login", {
        username,
        password,
      });

      setUserDetails(response.data); // Store user details
      setError(""); // Clear any errors
    } catch (error) {
      setError(error.response?.data || "Login failed");
      setUserDetails(null);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {userDetails && (
        <div>
          <h3>Welcome, {userDetails.fullName}!</h3>
          <p>Address: {userDetails.address}</p>
          <p>Contact: {userDetails.contact}</p>
          <p>Driver License: {userDetails.driver_license_number}</p>
        </div>
      )}
    </div>
  );
};

export default Login;

import React from "react";
import { useAtom } from "jotai";
import { userAtom } from "../atoms/userAtom";

const Dashboard = () => {
  const [user] = useAtom(userAtom);

  if (!user) {
    return <h2>Please log in to access the dashboard</h2>;
  }

  const handleLogout = () => {
    localStorage.removeItem("user");
    window.location.reload();
  };

  return (
    <div>
      <h1>Dashboard</h1>
      <p>Name: {user.fullName}</p>
      <p>Address: {user.address}</p>
      <p>Contact: {user.contact}</p>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Dashboard;

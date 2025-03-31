import React from "react";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
    const navigate = useNavigate();

    return (
        <div>
            <h1>Welcome to Vroom Car Rental</h1>
            <button onClick={() => navigate("/users")}>Manage Users</button>
            <button onClick={() => navigate("/cars")}>Manage Cars</button>
        </div>
    );
};

export default HomePage;

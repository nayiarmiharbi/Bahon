import React from "react";
import { useAtom } from "jotai";
import { userAtom, userAtomWithPersistence } from "../atoms/userAtom";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
    const [user, setUser] = useAtom(userAtom);
    const navigate = useNavigate(); // Initialize navigate function

    const handleLogout = () => {
        navigate("/"); // Redirect to home page
        localStorage.removeItem("user"); // Remove user from localStorage
        setUser(null); // Remove user from state & localStorage
    };

    return (
    <nav style={{ display: "flex", justifyContent: "space-between", padding: "10px", background: "#ddd" }}>
        <h2>Car Rental App</h2>
        <div>
        <Link to="/">Home</Link> | 
        {!user ? (
            <Link to="/login"> Login</Link>
        ) : (
            <>
            <Link to="/dashboard"> Dashboard</Link> |
            <button onClick={handleLogout}>Logout</button>
            </>
        )}
        </div>
        <div>
        {user ? (
            <span>Welcome, {user.fullName} ({user.uid})</span>
        ) : (
            <span>Please log in</span>
        )}
        </div>
    </nav>
    );
};

export default Navbar;

import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createUser, getUserById, updateUser } from "../../services/api";

const UserForm = () => {
    const [user, setUser] = useState({ username: "", password: "" });
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getUserById(id).then(response => setUser(response.data));
        }
    }, [id]);

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (id) {
            await updateUser(id, user);
        } else {
            await createUser(user);
        }
        navigate("/users");
    };

    return (
        <div>
            <h2>{id ? "Edit" : "Add"} User</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="username" value={user.username} onChange={handleChange} placeholder="Username" required />
                <input type="password" name="password" value={user.password} onChange={handleChange} placeholder="Password" required />
                <select name="userType" value={user.userType} onChange={handleChange} required>
                    <option value="">Select User Type</option>
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                </select>
                <button type="submit">{id ? "Update" : "Create"}</button>
            </form>
        </div>
    );
};

export default UserForm;

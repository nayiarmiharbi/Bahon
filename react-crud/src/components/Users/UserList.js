import React, { useEffect, useState } from "react";
import { getUsers, deleteUser } from "../../services/api";
import { useNavigate } from "react-router-dom";

const UserList = () => {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        const response = await getUsers();
        setUsers(response.data);
    };

    const handleDelete = async (id) => {
        await deleteUser(id);
        fetchUsers();
    };

    return (
        <div>
            <h2>Users List</h2>
            <button onClick={() => navigate("/users/add")}>Add New User</button>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        console.log(user),
                        console.log(user.uid),
                        <tr key={user.uid}>
                            <td>{user.uid}</td>
                            <td>{user.userType}</td>
                            <td>{user.username}</td>
                            <td>
                                <button onClick={() => navigate(`/users/edit/${user.uid}`)}>Edit</button>
                                <button onClick={() => handleDelete(user.uid)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;

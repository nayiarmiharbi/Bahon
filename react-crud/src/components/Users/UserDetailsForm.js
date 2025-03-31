import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createUserDetails, updateUserDetails, getUserDetails } from "../../services/api";
import { toast } from "react-toastify";

const UserDetailsForm = () => {
    const { id } = useParams(); // Always present
    const navigate = useNavigate();
    
    const [userDetails, setUserDetails] = useState({
        fullName: "",
        address: "",
        contact: "",
        driverLicenseNumber: "",
        uid: id // Set UID from URL param
    });

    const [isNew, setIsNew] = useState(false); // Track if user details exist

    useEffect(() => {
        setUserDetails(prevDetails => ({ ...prevDetails, UID: id })); // Ensure UID is always set

        getUserDetails(id)
            .then(response => {
                if (response.data) {
                    setUserDetails(response.data);
                    setIsNew(false); // Details exist → Update mode
                } else {
                    setIsNew(true); // No details → Create mode
                }
            })
            .catch(error => {
                console.error("Error fetching user details:", error);
                setIsNew(true); // Assume new details if fetch fails (404)
            });
    }, [id]);

    const handleChange = (e) => {
        // setUserDetails({UID: id, ...userDetails, [e.target.name]: e.target.value });
        setUserDetails({ ...userDetails, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isNew) {
                await createUserDetails(userDetails); // Create new details
            } else {
                await updateUserDetails(id, userDetails); // Update existing details
            }
            navigate("/users");
        } catch (error) {
                toast.error("Something went wrong!");
        }
    };

    return (
        console.log(userDetails),
        <div>
            <h2>{isNew ? "Add" : "Edit"} User Details</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="fullName" value={userDetails.fullName} onChange={handleChange} placeholder="Full Name" required />
                <input type="text" name="address" value={userDetails.address} onChange={handleChange} placeholder="Address" required />
                <input type="text" name="contact" value={userDetails.contact} onChange={handleChange} placeholder="Contact" required />
                <input type="text" name="driverLicenseNumber" value={userDetails.driverLicenseNumber} onChange={handleChange} placeholder="Driver License Number" required />
                <button type="submit">{isNew ? "Create" : "Update"}</button>
            </form>
        </div>
    );
};

export default UserDetailsForm;

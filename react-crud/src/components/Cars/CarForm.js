import React, {useState, useEffect} from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCarByRegNo, updateCar } from "../../services/api";

const CarForm = () => {
    const { regNo } = useParams(); // Always present
    const navigate = useNavigate();

    const [car, setCar] = useState({
        regNo: "",
        ownerId: "",
        model: "",
        capacity: "",
        rate: "",
        status: "",
        fuelType: ""
    });

    useEffect(() => {
        getCarByRegNo(regNo)
            .then(response => {
                setCar(response.data);
            })
            .catch(error => {
                console.error("Error fetching car details:", error);
            });
    }, [regNo]);

    const handleChange = (e) => {
        setCar({ ...car, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateCar(regNo, car); // Update existing car details
            navigate("/cars");
        } catch (error) {
            console.error("Error updating car:", error);
        }
    };

    return (
        console.log(car),
        console.log(regNo),
        <div>
            <h2>Edit Car</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="regNo" value={car.regNo} onChange={handleChange} placeholder="Registration No" required />
                <input type="text" name="ownerId" value={car.ownerId} onChange={handleChange} placeholder="Owner ID" required />
                <input type="text" name="model" value={car.model} onChange={handleChange} placeholder="Model" required />
                <input type="number" name="capacity" value={car.capacity} onChange={handleChange} placeholder="Capacity" required />
                <input type="number" name="rate" value={car.rate} onChange={handleChange} placeholder="Rate" required />
                <select name="status" value={car.status} onChange={handleChange} required>
                    <option value="">Select Status</option>
                    <option value="Available">Available</option>
                    <option value="Booked">Booked</option>
                    <option value="Maintenance">Maintenance</option>
                </select>
                <select name="fuelType" value={car.fuelType} onChange={handleChange} required>
                    <option value="">Select Fuel Type</option>
                    <option value="Petrol">Petrol</option>
                    <option value="Diesel">Diesel</option>
                    <option value="Electric">Electric</option>
                </select>
                <button type="button" onClick={() => navigate("/cars")}>Cancel</button>
                <button type="submit">Update</button>
            </form>
        </div>
    );
}
export default CarForm;
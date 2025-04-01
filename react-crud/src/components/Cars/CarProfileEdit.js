import React, {useState, useEffect} from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCarByRegNo, updateCar } from "../../services/api";

const CarProfileEdit = () => {
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
    }, [regNo, car]);

    const handleChange = (e) => {
        setCar({ ...car, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateCar(regNo, car); // Update existing car details
            navigate("/owner");
        } catch (error) {
            console.error("Error updating car:", error);
        }
    };

    return (
        console.log(car),
        console.log(regNo),
        <div>
            <h2>Edit Car</h2>
            <h1>Editing your Car information requires verification from Admin</h1>
            <div>
                <h3>Car Registration No: {car.regNo}</h3>
                <h3>Car Model: {car.model}</h3>
                <h3>Car Capacity: {car.capacity}</h3>
                <h3>Car Rate: {car.rate}</h3>
                <h3>Car Fuel Type: {car.fuelType}</h3>
            </div>
            <form onSubmit={handleSubmit}>
                <input type="text" name="model" value={car.model} onChange={handleChange} placeholder="Model" required />
                <input type="number" name="capacity" value={car.capacity} onChange={handleChange} placeholder="Capacity" required />
                <input type="number" name="rate" value={car.rate} onChange={handleChange} placeholder="Rate" required />
                
                <select name="fuelType" value={car.fuelType} onChange={handleChange} required>
                    <option value="">Select Fuel Type</option>
                    <option value="Petrol">Petrol</option>
                    <option value="Diesel">Diesel</option>
                    <option value="Electric">Electric</option>
                </select>
                <button type="button" onClick={() => navigate("/owner")}>Cancel</button>
                <button type="submit">Update</button>
            </form>
            <button onClick={handleDelete}>Delete this car</button>
            <button onClick={addInsurance}>Add Insurance</button>
            <button onClick={addMaintenance}>Claim for Maintenance</button>
        </div>
    );
}
export default CarProfileEdit;
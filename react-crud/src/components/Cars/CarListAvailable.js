import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { deleteCar, getCarsByFuel, getCarsByStatus } from '../../services/api';

const CarListAvailable = () => {
    const [fuelType, setFuelType] = useState("");
    const [cars, setCars] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchCars();
    }, [fuelType]);

    const fetchCars = async () => {
        if(fuelType === "") {
            const response = await getCarsByStatus("available");
            setCars(response.data);
        } else {
            const response = await getCarsByFuel(fuelType);
            setCars(response.data);
        }
    };

    const handleDelete = async (regNo) => {
        await deleteCar(regNo);
        fetchCars();
    };

    const handleChange = async (e) => {
        setFuelType(e.target.value);
    }

    return (
        <div>
            <h2>Available Cars List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Registration</th>
                        <th>OwnerId</th>
                        <th>Model</th>
                        <th>Capacity</th>
                        <th>Rate</th>
                        <th>Status</th>
                        <th>
                            <select name="fuelType" value={fuelType} onChange={handleChange} required>
                                <option value="">All</option>
                                <option value="Electric">Electric</option>
                                <option value="Petrol">Petrol</option>
                                <option value="Diesel">Diesel</option>
                            </select>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {cars.map((car) => (
                        console.log(car),
                        console.log(car.regNo),
                        <tr key={car.regNo}>
                            <td>{car.regNo}</td>
                            <td>{car.ownerId}</td>
                            <td>{car.model}</td>
                            <td>{car.capacity}</td>
                            <td>{car.rate}</td>
                            <td>{car.status}</td>
                            <td>{car.fuelType}</td>
                            <td>
                            <button onClick={() => navigate(`/cars/profile/${car.regNo}`)}>Show Profile</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default CarListAvailable;
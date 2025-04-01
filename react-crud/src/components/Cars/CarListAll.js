import React, {useEffect, useState} from "react";
import { getAllCars, getCarsByFuel, getCarsByStatus } from "../../services/api";
import { useNavigate } from "react-router-dom";

const CarListAll = () => {
    const [fuelType, setFuelType] = useState("");
    const [status, setStatus] = useState("");
    const [cars, setCars] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchCars();
    }, [fuelType, status]);

    const fetchCars = async () => {
        if (fuelType !== "") {
            const response = await getCarsByFuel(fuelType);
            setCars(response.data);
        }
        else if (status !== "") {
            const response = await getCarsByStatus(status);
            setCars(response.data);
        } else {
            const response = await getAllCars();
            setCars(response.data);
        }
    };

    return (
        <div>
            <h2>All Cars List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Registration</th>
                        <th>OwnerId</th>
                        <th>Model</th>
                        <th>Capacity</th>
                        <th>Rate</th>
                        <th>
                            <button onClick={() => setStatus("available")}>Show Available</button>
                        </th>
                        <th>
                            <select name="fuelType" value={fuelType} onChange={(e) => setFuelType(e.target.value)} required>
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
export default CarListAll;
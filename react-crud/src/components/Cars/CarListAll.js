import React, {useEffect, useState} from "react";
import { getAllCars, deleteCar } from "../../services/api";
import { useNavigate } from "react-router-dom";

const CarListAll = () => {
    const [cars, setCars] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        const response = await getAllCars();
        setCars(response.data);
    };

    const handleDelete = async (regNo) => {
        await deleteCar(regNo);
        fetchCars();
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
                        <th>Status</th>
                        <th>Fuel</th>
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
                                <button onClick={() => navigate(`/cars/edit/${car.regNo}`)}>Edit</button>
                                <button onClick={() => handleDelete(car.regNo)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
export default CarListAll;
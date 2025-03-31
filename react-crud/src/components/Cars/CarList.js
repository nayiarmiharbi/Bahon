import React, { useState, useEffect } from 'react'
import { useAtom } from 'jotai'
import { useNavigate } from 'react-router-dom'
import { userAtom } from '../../atoms/userAtom'
import { getCarsByOwner, deleteCar } from '../../services/api';

const CarList = () => {
    const [user] = useAtom(userAtom);
    const [cars, setCars] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        const response = await getCarsByOwner(user.uid);
        setCars(response.data);
    };

    const handleDelete = async (regNo) => {
        await deleteCar(regNo);
        fetchCars();
    };

    return (
        <div>
            <h2>{user?.fullName}'s Cars List</h2>
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

export default CarList;
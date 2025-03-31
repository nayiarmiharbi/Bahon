package com.raiyan.crud.service;

import com.raiyan.crud.model.Car;
import com.raiyan.crud.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public List<Car> getCarsByOwner(int id) {
        return carRepository.getCarsByOwnerId(id);
    }

    public Optional<Car> getCarByRegNo(String regNo) {
        return carRepository.getCarByRegNo(regNo);
    }

    public Car createCar(Car car) {
        carRepository.saveCar(car);
        return car;
    }

    public Car updateCar(String regNo, Car car) {
        Optional<Car> existingCar = carRepository.getCarByRegNo(regNo);
        if (existingCar.isPresent()) {
            carRepository.updateCar(regNo, car);
            return car;
        } else {
            throw new RuntimeException("Car not found with Registration Number: " + regNo);
        }
    }

    public void deleteCar(String regNo) {
        carRepository.deleteCar(regNo);
    }
}

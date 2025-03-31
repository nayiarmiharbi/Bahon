package com.raiyan.crud.controller;

import com.raiyan.crud.model.Car;
import com.raiyan.crud.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cars")
public class CarController {

    private static final Logger logger = Logger.getLogger(CarController.class.getName());

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        if (cars.isEmpty()) {
            logger.info("No cars found.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Car>> getCarsByOwner(@PathVariable int id) {
        List<Car> cars = carService.getCarsByOwner(id);
        if (cars.isEmpty()) {
            logger.info("No cars found for owner with ID " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/regno/{regNo}")
    public ResponseEntity<Car> getCarByRegNo(@PathVariable String regNo) {
        Optional<Car> car = carService.getCarByRegNo(regNo);
        return car.map(ResponseEntity::ok)
                  .orElseGet(() -> {
                      logger.warning("Car with Registration Number " + regNo + " not found.");
                      return ResponseEntity.notFound().build();
                  });
    }

    @PostMapping("/{id}")
    public ResponseEntity<Car> createCar(@PathVariable int id, @RequestBody Car car) {
        car.setOwnerId(id); // Set the owner ID for the car
        Car createdCar = carService.createCar(car);
        logger.info("Car created successfully with Registration Number: " + createdCar.getRegNo());
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/regno/{regNo}")
    public ResponseEntity<Car> updateCar(@PathVariable String regNo, @RequestBody Car car) {
        Optional<Car> existingCar = carService.getCarByRegNo(regNo);
        if (existingCar.isPresent()) {
            Car updatedCar = carService.updateCar(regNo, car);
            logger.info("Car with Registration Number " + regNo + " updated successfully.");
            return ResponseEntity.ok(updatedCar);
        } else {
            logger.warning("Car with Registration Number " + regNo + " not found for update.");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/regno/{regNo}")
    public ResponseEntity<Void> deleteCar(@PathVariable String regNo) {
        Optional<Car> existingCar = carService.getCarByRegNo(regNo);
        if (existingCar.isPresent()) {
            carService.deleteCar(regNo);
            logger.info("Car with Registration Number " + regNo + " deleted successfully.");
            return ResponseEntity.noContent().build();
        } else {
            logger.warning("Car with Registration Number " + regNo + " not found for deletion.");
            return ResponseEntity.notFound().build();
        }
    }
}

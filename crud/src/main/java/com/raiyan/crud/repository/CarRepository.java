package com.raiyan.crud.repository;

import com.raiyan.crud.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Car> rowMapper = (rs, rowNum) -> new Car(
            rs.getString("regNo"),
            rs.getInt("owner_id"),
            rs.getString("model"),
            rs.getInt("capacity"),
            rs.getDouble("rate"),
            rs.getString("status"),
            rs.getString("fuelType")
    );

    // Insert Car
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars (regNo, owner_id, model, capacity, rate, status, fuelType) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getRegNo(), car.getOwnerId(), car.getModel(), car.getCapacity(), car.getRate(), car.getStatus(), car.getFuelType());
    }

    // Get All Cars
    public List<Car> getAllCars() {
        String sql = "SELECT * FROM Cars";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Get Car by ownerID
    public List<Car> getCarsByOwnerId(int ownerId) {
        String sql = "SELECT * FROM cars WHERE owner_id = ?";
        return jdbcTemplate.query(sql, rowMapper, ownerId);
    }
    

    // Update Car
    public void updateCar(String regNo, Car car) {
        String sql = "UPDATE cars SET owner_id = ?, model = ?, capacity = ?, rate = ?, status = ?, fuelType = ? WHERE regNo = ?";
        jdbcTemplate.update(sql, car.getOwnerId(), car.getModel(), car.getCapacity(), car.getRate(), car.getStatus(), car.getFuelType(), regNo);
    }

    // Delete Car
    public void deleteCar(String regNo) {
        String sql = "DELETE FROM cars WHERE regNo = ?";
        jdbcTemplate.update(sql, regNo);
    }

    // Get Car by regNo
    public Optional<Car> getCarByRegNo(String regNo) {
        String sql = "SELECT * FROM cars WHERE regNo = ?";
        List<Car> cars = jdbcTemplate.query(sql, rowMapper, regNo);
        return cars.stream().findFirst();
    }

    // Get Cars Available for Booking
    public List<Car> getAvailableCars() {
        String sql = "SELECT * FROM cars WHERE status = 'Available'";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Get not Available Cars
    public List<Car> getNotAvailableCars() {
        String sql = "SELECT * FROM cars WHERE status != 'Available'";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Get Cars by Fuel Type
    public List<Car> getCarsByFuelType(String fuelType) {
        String sql = "SELECT * FROM cars WHERE fuelType = ? AND status = 'Available'";
        return jdbcTemplate.query(sql, rowMapper, fuelType);
    }

    // Get Cars by Capacity
    public List<Car> getCarsByCapacity(int capacity) {
        String sql = "SELECT * FROM cars WHERE capacity = ? AND status = 'Available'";
        return jdbcTemplate.query(sql, rowMapper, capacity);
    }

    // Get Cars by Rate range
    public List<Car> getCarsByRate(double rate) {
        String sql = "SELECT * FROM cars WHERE rate <= ? AND status = 'Available'";
        return jdbcTemplate.query(sql, rowMapper, rate);
    }

    // Get cars by model alike 
    public List<Car> getCarsByModel(String model) {
        String sql = "SELECT * FROM cars WHERE model LIKE ? AND status = 'Available'";
        return jdbcTemplate.query(sql, rowMapper, "%" + model + "%");
    }
}

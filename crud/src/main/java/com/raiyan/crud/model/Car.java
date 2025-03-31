package com.raiyan.crud.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String regNo;
    private int ownerId;
    private String model;
    private int capacity;
    private double rate;
    private String status; // ENUM ('Available', 'Rented', 'Under Maintenance')
    private String fuelType; // ENUM ('Petrol', 'Diesel', 'Electric')
}


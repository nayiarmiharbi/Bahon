package com.raiyan.crud.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private int rentID;
    private Integer BID; // Can be null if booking is removed
    private Integer customerId; // Can be null if customer is removed
    private double totalAmount;
    private String status; // ENUM ('Active', 'Completed', 'Canceled')
}

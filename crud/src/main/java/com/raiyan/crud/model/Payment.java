package com.raiyan.crud.model;

import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private int PID;
    private int rentID;
    private double amount;
    private Date paymentDate;
    private String paymentMethod; // ENUM ('Credit Card', 'Cash', 'Bank Transfer')
    private String status; // ENUM ('Paid', 'Pending', 'Failed')
    private String transactionID;
}

package com.raiyan.crud.model;

import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    private int IID;
    private String regNo;
    private String providerName;
    private String policyNumber;
    private double coverageAmount;
    private Date startDate;
    private Date endDate;
}

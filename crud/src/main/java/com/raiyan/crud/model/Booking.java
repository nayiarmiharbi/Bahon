package com.raiyan.crud.model;

import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private int BID;
    private String regNo;
    private Date fromDate;
    private Date tillDate;
}

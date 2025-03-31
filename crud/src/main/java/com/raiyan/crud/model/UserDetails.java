package com.raiyan.crud.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetails {
    private int UID;
    private String fullName;
    private String address;
    private String contact;
    private String driverLicenseNumber; // Changed to camelCase for Java conventions
}

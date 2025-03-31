package com.raiyan.crud.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int UID;
    private int userType;
    private String username;
    private String password;
}

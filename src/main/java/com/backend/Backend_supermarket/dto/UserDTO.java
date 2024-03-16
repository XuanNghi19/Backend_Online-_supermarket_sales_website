package com.backend.Backend_supermarket.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;
    private String fullName;
    private String role;
    private Boolean active;
}

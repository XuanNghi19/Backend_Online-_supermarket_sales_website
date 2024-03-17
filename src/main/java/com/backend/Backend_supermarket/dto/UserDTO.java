package com.backend.Backend_supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @Min(value = 3, message = "Mật khẩu tối thiểu là 3 kí tự")
    @JsonProperty("password") 
    private String password;

    @NotBlank
    @Min(value = 8, message = "Số điện tối thiểu là 8 số")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("fullname")
    private String fullName;
} 

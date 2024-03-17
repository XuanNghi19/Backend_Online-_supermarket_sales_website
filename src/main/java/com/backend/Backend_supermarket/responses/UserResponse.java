package com.backend.Backend_supermarket.responses;

import java.sql.Date;

import com.backend.Backend_supermarket.dto.UserDTO;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password") 
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("active")
    private Boolean active; 

    public static UserResponse fromUser(User user){
        return UserResponse.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .phoneNumber(user.getPhoneNumber())
            .address(user.getAddress())
            .dateOfBirth(user.getDateOfBirth())
            .fullName(user.getFullName())
            .active(user.getActive())
            .build();
    }
}

package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDTO {
    @Min(value = 3, message = "Mật khẩu ít nhất phải có 3 chữ cái!")
    @JsonProperty("password") 
    private String password;

    @Min(value = 3, message = "Mật khẩu ít nhất phải có 3 chữ cái!")
    @JsonProperty("new_password") 
    private String newPassword;
}

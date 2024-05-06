package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank
    @Size(min = 3, message = "Mật khẩu tối thiểu là 3 kí tự")
    @JsonProperty("password") 
    private String password;

    @NotBlank
    @Pattern(regexp = "\\d{8,}", message = "Số điện tối thiểu là 8 số")
    @JsonProperty("phone_number")
    private String phoneNumber;
}

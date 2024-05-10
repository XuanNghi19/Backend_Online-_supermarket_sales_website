package com.backend.Backend_supermarket.dtos;

import com.backend.Backend_supermarket.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerUserDTO {
    @NotNull
    @Email
    @JsonProperty("email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Mật khẩu tối thiểu là 3 kí tự")
    @JsonProperty("password")
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{8,}", message = "Số điện tối thiểu là 8 số")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull
    @JsonProperty("address")
    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("role")
    private Role role;
}

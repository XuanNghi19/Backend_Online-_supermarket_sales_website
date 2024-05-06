package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagementUserResponse {
    @JsonProperty("id")
    private Long id;

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

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("active")
    private Boolean active;

    // cho admin
    public static ManagementUserResponse fromUser(User user) {
        return ManagementUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .avatar(user.getAvatar())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }
}

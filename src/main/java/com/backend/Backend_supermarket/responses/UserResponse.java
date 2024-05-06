package com.backend.Backend_supermarket.responses;

import java.util.Date;

import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

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
    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
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

package com.backend.Backend_supermarket.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user")
    private UserResponse user;
}

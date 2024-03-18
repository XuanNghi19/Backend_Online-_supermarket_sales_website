package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.responses.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllUser();
}

package com.backend.Backend_supermarket.service;

import com.backend.Backend_supermarket.responses.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllUser();
}

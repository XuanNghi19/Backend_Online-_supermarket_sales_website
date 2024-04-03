package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse register(UserDTO userDTO) throws Exception;
    UserResponse updateUser(Long id, UserDTO userDTO)  throws Exception;
    void deleteUser(Long id);
    String login(String phoneNumber, String password) throws Exception;
}

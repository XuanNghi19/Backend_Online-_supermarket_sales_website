package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.UpdatePasswordDTO;
import com.backend.Backend_supermarket.dtos.UpdateUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.LoginResponse;
import com.backend.Backend_supermarket.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse register(UserDTO userDTO) throws Exception;
    void deleteUser(Long id);
    LoginResponse login(String phoneNumber, String password) throws Exception;
    UserResponse getUserDetail(String token) throws Exception;
    UserResponse updateUserDetail(String token, UpdateUserDTO updateUserDTO) throws Exception;
    void updatePassword(String token, UpdatePasswordDTO updatePasswordDTO) throws Exception;
    void uploadAvatar(String token, String avatarFileName) throws Exception;
    boolean isPresentAvatarFileName(String avatarFileName);
}

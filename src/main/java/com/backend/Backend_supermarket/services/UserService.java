package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.dtos.UpdatePasswordDTO;
import com.backend.Backend_supermarket.dtos.UpdateUserDTO;
import com.backend.Backend_supermarket.responses.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllClient();
    public Page<UserResponse> getAllClientWithPagination(int page, int pageSize);
    public List<UserResponse> getAllStaff();
    public Page<UserResponse> getAllStaffWithPagination(int page, int pageSize);
    public List<UserResponse> getAllStaffByRole(Role role);
    public Page<UserResponse> getAllStaffByRoleWithPagination(Role role, int page, int pageSize);
    public ManagementUserResponse getUserInformation(Long userId);
    public String createUser(UserDTO userDTO) throws Exception;
    public String updateUser(Long userId, UserDTO userDTO) throws Exception;
    
    UserResponse register(UserDTO userDTO) throws Exception;
    void deleteUser(Long id);
    String login(String phoneNumber, String password) throws Exception;
    UserResponse getUserDetail(String token) throws Exception;
    UserResponse updateUserDetail(String token, UpdateUserDTO updateUserDTO) throws Exception;
    void updatePassword(String token, UpdatePasswordDTO updatePasswordDTO) throws Exception;
    void uploadAvatar(String token, String avatarFileName) throws Exception;
    boolean isPresentAvatarFileName(String avatarFileName);
}

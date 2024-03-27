package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllClient();
    public Page<UserResponse> getAllClientWithPagination(int page, int pageSize);
    public List<UserResponse> getAllStaff();
    public Page<UserResponse> getAllStaffWithPagination(int page, int pageSize);
    public List<UserResponse> getAllStaffByRole(String role);
    public Page<UserResponse> getAllStaffByRoleWithPagination(String role, int page, int pageSize);
    public ManagementUserResponse getUserInformation(Long userId);
    public String createUser(UserDTO userDTO) throws Exception;
    public String updateUser(Long userId, UserDTO userDTO) throws Exception;
    public String deleteUser(Long userId) throws Exception;
}

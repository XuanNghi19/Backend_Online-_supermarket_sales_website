package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.ManagerUserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagerUserService {
    public User findUserById(Long id);
    public List<ManagementUserResponse> getAllClient();
    public Page<ManagementUserResponse> getAllClientWithPagination(int page, int pageSize);
    public List<ManagementUserResponse> getAllStaff();
    public List<ManagementUserResponse> getAllStaffByRole(Role role);
    public Page<ManagementUserResponse> getAllStaffWithPagination(int page, int pageSize);
    public ManagementUserResponse getUserInformation(Long userId);
    public Page<ManagementUserResponse> getAllStaffByRoleWithPagination(Role role, int page, int pageSize);
    public ManagementUserResponse createUser(ManagerUserDTO managerUserDTO) throws Exception;
    public ManagementUserResponse updateUser(Long userId, ManagerUserDTO managerUserDTO) throws Exception;
    public String changePassword(Long userId, String password) throws Exception ;
    public void deleteUser(Long id);
}

package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.dtos.ManagerUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.MessageResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.ManagerUserService;
import com.backend.Backend_supermarket.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/managementUser")
public class ManagementUserController {

    private final ManagerUserService managerUserService;

    // lay danh sach khach hang
    @GetMapping("/clients")
    public ResponseEntity<?> getAllClient() {
        try {
            List<ManagementUserResponse> clientList = managerUserService.getAllClient();
            return ResponseEntity.ok().body(clientList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/clients/{page}/{pageSize}")
    public ResponseEntity<?> getAllClientWithPagination(
            @PathVariable int page,
            @PathVariable int pageSize
    ) {
        try {
            Page<ManagementUserResponse> clientList = managerUserService.getAllClientWithPagination(page, pageSize);
            return ResponseEntity.ok().body(clientList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // lay danh sach nhan vien
    @GetMapping("/staffs")
    public ResponseEntity<?> getAllStaff() {
        try {
            List<ManagementUserResponse> staffList = managerUserService.getAllStaff();
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/staffs/{page}/{pageSize}")
    public ResponseEntity<?> getAllStaffWithPagination(
            @PathVariable int page,
            @PathVariable int pageSize
    ) {
        try {
            Page<ManagementUserResponse> staffList = managerUserService.getAllStaffWithPagination(page, pageSize);
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // loc danh sach nhan vien
    @GetMapping("/staffs/{role}")
    public ResponseEntity<?> getAllStaffByRole(@PathVariable("role") Role staffRole) {
        try {
            List<ManagementUserResponse> staffList = managerUserService.getAllStaffByRole(staffRole);
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/staffs/{role}/{page}/{pageSize}")
    public ResponseEntity<?> getAllStaffByRoleWithPagination(
            @PathVariable Role role,
            @PathVariable int page,
            @PathVariable int pageSize
    ) {
        try {
            Page<ManagementUserResponse> staffList = managerUserService.getAllStaffByRoleWithPagination(role, page, pageSize);
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // xem chi tiet thong tin nguoi dung cua admin
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInformation(@PathVariable("id") Long userId) {
        try {
            ManagementUserResponse managementUserResponse = managerUserService.getUserInformation(userId);
            return ResponseEntity.ok().body(managementUserResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createStaff(
            @RequestBody @Valid ManagerUserDTO managerUserDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ManagementUserResponse managementUserResponse = managerUserService.createUser(managerUserDTO);
            return ResponseEntity.ok().body(managementUserResponse);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> managerUpdateUser(
            @PathVariable("id") Long userId,
            @RequestBody @Valid ManagerUserDTO managerUserDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ManagementUserResponse managementUserResponse = managerUserService.updateUser(userId, managerUserDTO);
            return ResponseEntity.ok().body(managementUserResponse);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }
    }
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable("id") Long userId,
            @RequestParam("password") String password
    ) {
        try {
            String pass = managerUserService.changePassword(userId, password);
            return ResponseEntity.ok().body(pass);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> managerDeleteUser(
            @PathVariable("id") Long userId
    ) {
        try {
            managerUserService.deleteUser(userId);
            return ResponseEntity.ok(new MessageResponse("Xoa thanh cong!"));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}

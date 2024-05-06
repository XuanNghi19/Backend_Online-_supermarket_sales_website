package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.MessageResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
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
@RequestMapping("${api.prefix}/userManagement")
public class ManagementUserController {

    private final UserService userService;

    // lay danh sach khach hang
    @GetMapping("/clients")
    public ResponseEntity<?> getAllClient() {
        try {
            List<UserResponse> clientList = userService.getAllClient();
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
            Page<UserResponse> clientList = userService.getAllClientWithPagination(page, pageSize);
            return ResponseEntity.ok().body(clientList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // lay danh sach nhan vien
    @GetMapping("/staffs")
    public ResponseEntity<?> getAllStaff() {
        try {
            List<UserResponse> staffList = userService.getAllStaff();
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
            Page<UserResponse> staffList = userService.getAllStaffWithPagination(page, pageSize);
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // loc danh sach nhan vien
    @GetMapping("/staffs/{role}")
    public ResponseEntity<?> getAllStaffByRole(@PathVariable("role") Role staffRole) {
        try {
            List<UserResponse> staffList = userService.getAllStaffByRole(staffRole);
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
            Page<UserResponse> staffList = userService.getAllStaffByRoleWithPagination(role, page, pageSize);
            return ResponseEntity.ok().body(staffList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // xem chi tiet thong tin nguoi dung cua admin
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInformation(@PathVariable("id") Long userId) {
        try {
            ManagementUserResponse managementUserResponse = userService.getUserInformation(userId);
            return ResponseEntity.ok().body(managementUserResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createStaff(
            @RequestBody @Valid UserDTO userDTO,
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
            String string = userService.createUser(userDTO);
            return ResponseEntity.ok(new MessageResponse(string));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody @Valid UserDTO userDTO,
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
            String string = userService.updateUser(userId, userDTO);
            return ResponseEntity.ok(new MessageResponse(string));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") Long userId
    ) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new MessageResponse());
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}

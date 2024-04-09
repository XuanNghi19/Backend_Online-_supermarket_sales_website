package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.LoginDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody @Valid UserDTO userDTO,
        BindingResult result
    ){
        try {
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages);
            }
            UserResponse response = userService.register(userDTO);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody @Valid LoginDTO loginDTO,
        BindingResult result
    ){
        try {
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = userService.login(loginDTO.getPhoneNumber(), loginDTO.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{token}")

    public ResponseEntity<?> getUserDetail(
        @PathVariable("token") String token,
        BindingResult result
    ){
        try {
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages);
            }

            token = token.substring(7);
            UserResponse response = userService.getUserDetail(token);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

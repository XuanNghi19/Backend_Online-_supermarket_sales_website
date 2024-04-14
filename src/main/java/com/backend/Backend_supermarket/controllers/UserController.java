package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.LoginDTO;
import com.backend.Backend_supermarket.dtos.UpdatePasswordDTO;
import com.backend.Backend_supermarket.dtos.UpdateUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.ResponseData;
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
    public ResponseData<?> register(
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
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
            }
            UserResponse response = userService.register(userDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Register successfully",response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseData<?> login(
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
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
                // return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = userService.login(loginDTO.getPhoneNumber(), loginDTO.getPassword());
            // return ResponseEntity.ok().body(token);
            return new ResponseData<>(HttpStatus.OK.value(), "Login successfully",token);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            // return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseData<?> getUserDetail(
        @RequestHeader("Authorization") String authorizationHeader
    ){
        try {
            String token = authorizationHeader.substring(7);
            UserResponse response = userService.getUserDetail(token);
            return new ResponseData<>(HttpStatus.OK.value(), "Get user successfully",response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/details")
    public ResponseData<?> updateUserDetail(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody @Valid UpdateUserDTO updateUserDTO 
    ){
        try {
            String token = authorizationHeader.substring(7);
            UserResponse response = userService.updateUserDetail(token, updateUserDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Update user successfully",response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/details/change-password")
    public ResponseData<?> changePassword(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody @Valid UpdatePasswordDTO updatePasswordDTO,
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
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
                // return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = authorizationHeader.substring(7);
            userService.updatePassword(token, updatePasswordDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Password is updated");
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}

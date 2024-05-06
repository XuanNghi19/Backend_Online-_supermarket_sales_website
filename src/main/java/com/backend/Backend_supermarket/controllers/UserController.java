package com.backend.Backend_supermarket.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Backend_supermarket.dtos.LoginDTO;
import com.backend.Backend_supermarket.dtos.UpdatePasswordDTO;
import com.backend.Backend_supermarket.dtos.UpdateUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.models.ProductImage;
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
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
            }
            UserResponse response = userService.register(userDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Register successfully", response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseData<?> login(
            @RequestBody @Valid LoginDTO loginDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                System.out.println("loooi");
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
                // return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = userService.login(loginDTO.getPhoneNumber(), loginDTO.getPassword());
            // return ResponseEntity.ok().body(token);
            return new ResponseData<>(HttpStatus.OK.value(), "Login successfully", token);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            // return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseData<?> getUserDetail(
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7);
            UserResponse response = userService.getUserDetail(token);
            return new ResponseData<>(HttpStatus.OK.value(), "Get user successfully", response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/details")
    public ResponseData<?> updateUserDetail(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        try {
            String token = authorizationHeader.substring(7);
            UserResponse response = userService.updateUserDetail(token, updateUserDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Update user successfully", response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/details/change-password")
    public ResponseData<?> changePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid UpdatePasswordDTO updatePasswordDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
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

    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProductImage(
            @RequestHeader("Authorization") String authorizationHeader,
            @ModelAttribute("file") MultipartFile file) {
        try {
            String token = authorizationHeader.substring(7);
            if (file.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Không tồn tại file ảnh!");
            if (file.getSize() > 10 * 1024 * 1024) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Dung lượng file ảnh quá lớn");
            }
            // kiểm tra xem có phải file ảnh không
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("Định dạng file phải ảnh file ảnh");
            }
            // lưu ảnh vào server
            String filename = storeImage(file);
            // lưu tên ảnh vào database
            userService.uploadAvatar(token, filename);
            return ResponseEntity.ok().body(filename);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/avatar/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String imageUrl) {
        try {
            if(userService.isPresentAvatarFileName(imageUrl)){
                Path path = Paths.get("uploads/users/" + imageUrl);
                UrlResource resource = new UrlResource(path.toUri());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeImage(MultipartFile file) throws IOException {
        // lấy ra đuôi của file
        String contentType = file.getContentType().substring(6);
        // Đổi tên file thành tên duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + contentType;
        // lấy ra đường dẫn thư mục uploads
        Path uploadDir = Paths.get("uploads/users");
        // kiểm tra thư mục đã tồn tại chưa
        if (!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }
        // đường dẫn tới file ảnh
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        // lưu file ảnh vào server
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}

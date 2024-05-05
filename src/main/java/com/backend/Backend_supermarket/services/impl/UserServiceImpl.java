package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllClient() {

        return userRepository.findByRoleAndActiveTrue(Role.USER)
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<UserResponse> getAllClientWithPagination(int page, int pageSize) {
        return  userRepository.findByRoleAndActiveTrue(
                Role.USER,
                PageRequest.of(page - 1, pageSize))
                .map(UserResponse::fromUser);
    }

    @Override
    public List<UserResponse> getAllStaff() {
        return userRepository.findByRoleNotAndActiveTrue(Role.USER)
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<UserResponse> getAllStaffWithPagination(int page, int pageSize) {
        return userRepository.findByRoleNotAndActiveTrue(
                Role.USER,
                PageRequest.of(page - 1, pageSize))
                .map(UserResponse::fromUser);
    }

    @Override
    public List<UserResponse> getAllStaffByRole(Role role) {
        return userRepository.findByRoleAndActiveTrue(role)
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<UserResponse> getAllStaffByRoleWithPagination(Role role, int page, int pageSize) {
        return userRepository.findByRoleAndActiveTrue(
                role,
                PageRequest.of(page - 1, pageSize))
                .map(UserResponse::fromUser);
    }

    @Override
    public ManagementUserResponse getUserInformation(Long userId) {
        return ManagementUserResponse.fromUser(
                userRepository.findById(userId)
                        .orElseThrow(
                                () -> new NoSuchElementException("Không tồn tại user với id : " + userId)
                        ));
    }

    @Override
    public String createUser(UserDTO userDTO) throws Exception {
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            return ("Email đã tồn tại!");
        }
        if(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())) {
            return ("Số điện thoai đã tồn tại!");
        }

        User newUser = User.fromUserDTO(userDTO);
        newUser.setActive(true);
        userRepository.save(newUser);

        return "Tạo mới tài khoản thành công!";
    }

    @Override
    public String updateUser(Long userId, UserDTO userDTO) throws Exception {

        if(!userRepository.existsById(userId)) {
            return ("Không tồn tại user với id : " + userId);
        }
        User user = userRepository.findById(userId).orElse(new User());
        if(userRepository.existsByEmail(userDTO.getEmail()) && !userDTO.getEmail().equals(user.getEmail())) {
            return ("Email đã tồn tại!");
        }
        if(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber()) && !userDTO.getPhoneNumber().equals(user.getPhoneNumber())) {
            return ("Số điện thoai đã tồn tại!");
        }

        User updateUser = User.fromUserDTO(userDTO);
        updateUser.setId(userId);
        userRepository.save(updateUser);
        return "Chỉnh sửa tài khoản thành công!";
    }

    @Override
    public String deleteUser(Long userId) throws Exception {
        User updateUser = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Không tồn tại user với id : " + userId)
        );
        updateUser.setActive(false);
        userRepository.save(updateUser);
        return "Xóa thành công!";
    }

}

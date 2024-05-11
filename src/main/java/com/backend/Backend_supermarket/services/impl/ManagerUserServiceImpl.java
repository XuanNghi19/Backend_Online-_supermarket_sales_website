package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
import com.backend.Backend_supermarket.dtos.ManagerUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.ManagerUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerUserServiceImpl implements ManagerUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Khong tim thay user voi " + id));
    }

    public List<ManagementUserResponse> getAllClient() {

        return userRepository.findByRoleAndActiveTrue(Role.USER)
                .stream()
                .map(ManagementUserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<ManagementUserResponse> getAllClientWithPagination(int page, int pageSize) {
        return  userRepository.findByRoleAndActiveTrue(
                        Role.USER,
                        PageRequest.of(page - 1, pageSize))
                .map(ManagementUserResponse::fromUser);
    }

    @Override
    public List<ManagementUserResponse> getAllStaff() {
        return userRepository.findByRoleNotAndActiveTrue(Role.USER)
                .stream()
                .map(ManagementUserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<ManagementUserResponse> getAllStaffWithPagination(int page, int pageSize) {
        return userRepository.findByRoleNotAndActiveTrue(
                        Role.USER,
                        PageRequest.of(page - 1, pageSize))
                .map(ManagementUserResponse::fromUser);
    }

    @Override
    public List<ManagementUserResponse> getAllStaffByRole(Role role) {
        return userRepository.findByRoleAndActiveTrue(role)
                .stream()
                .map(ManagementUserResponse::fromUser)
                .toList();
    }

    @Override
    public Page<ManagementUserResponse> getAllStaffByRoleWithPagination(Role role, int page, int pageSize) {
        return userRepository.findByRoleAndActiveTrue(
                        role,
                        PageRequest.of(page - 1, pageSize))
                .map(ManagementUserResponse::fromUser);
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
    public ManagementUserResponse createUser(ManagerUserDTO managerUserDTO) throws Exception {
        if(userRepository.existsByEmail(managerUserDTO.getEmail())) {
            throw new Exception("Email đã tồn tại!");
        }
        if(userRepository.existsByPhoneNumber(managerUserDTO.getPhoneNumber())) {
            throw new Exception("Số điện thoai đã tồn tại!");
        }

        User newUser = User.fromManagerUserDTO(managerUserDTO);
        newUser.setPassword(passwordEncoder.encode(managerUserDTO.getPassword()));
        newUser.setActive(true);
        ManagementUserResponse managementUserResponse = ManagementUserResponse.fromUser(userRepository.save(newUser));
        managementUserResponse.setPassword(managerUserDTO.getPassword());
        return ManagementUserResponse.fromUser(userRepository.save(newUser));
    }

    @Override
    public ManagementUserResponse updateUser(Long userId, ManagerUserDTO managerUserDTO) throws Exception {

        if(!userRepository.existsById(userId)) {
            throw new Exception ("Không tồn tại user với id : " + userId);
        }
        User updateUser = userRepository.findById(userId).orElse(new User());
        if(userRepository.existsByEmail(managerUserDTO.getEmail()) && !managerUserDTO.getEmail().equals(updateUser.getEmail())) {
            throw new Exception  ("Email đã tồn tại!");
        }
        if(userRepository.existsByPhoneNumber(managerUserDTO.getPhoneNumber()) && !managerUserDTO.getPhoneNumber().equals(updateUser.getPhoneNumber())) {
            throw new Exception  ("Số điện thoai đã tồn tại!");
        }

        if(!Objects.equals(managerUserDTO.getEmail(), "")) {
            updateUser.setEmail(managerUserDTO.getEmail());
        }
        if(!Objects.equals(managerUserDTO.getPhoneNumber(), "")) {
            updateUser.setPhoneNumber(managerUserDTO.getPhoneNumber());
        }
        if(!Objects.equals(managerUserDTO.getAddress(), "")) {
            updateUser.setAddress(managerUserDTO.getAddress());
        }
        if(managerUserDTO.getDateOfBirth() != null) {
            updateUser.setDateOfBirth(managerUserDTO.getDateOfBirth());
        }
        if(!Objects.equals(managerUserDTO.getFullName(), "")) {
            updateUser.setFullName(managerUserDTO.getFullName());
        }
        if(!Objects.equals(managerUserDTO.getAvatar(), "")) {
            updateUser.setAvatar(managerUserDTO.getAvatar());
        }
        if(managerUserDTO.getRole() != null) {
            updateUser.setRole(managerUserDTO.getRole());
        }

        return ManagementUserResponse.fromUser(userRepository.save(updateUser));
    }

    @Override
    public String changePassword(Long userId, String password) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Khong ton tai user voi " + userId));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return password;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            existingUser.get().setActive(false);
            userRepository.save(existingUser.get());
        }
    }
}

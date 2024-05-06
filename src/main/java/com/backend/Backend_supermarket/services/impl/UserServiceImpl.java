package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
import com.backend.Backend_supermarket.dtos.UpdatePasswordDTO;
import com.backend.Backend_supermarket.dtos.UpdateUserDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

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
    @Transactional
    @Override
    public UserResponse register(UserDTO userDTO) throws Exception {

        if(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())){
            throw new Exception("Số điện thoại đã tồn tại !");
        }

        User newUser = User.fromUserDTO(userDTO);
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setActive(true);
        newUser.setRole(Role.USER);

        User saveUser = userRepository.save(newUser);

        return UserResponse.fromUser(saveUser);
    }

    
    @Override
    public String login(String phoneNumber, String password) throws Exception {
        User existingUser = userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new Exception("Thông tin đăng nhập không chính xác!"));
        
        if(passwordEncoder.matches( password, existingUser.getPassword())){
            if(existingUser.getActive()){
                String token = jwtTokenUtils.generateToken(existingUser);
                return token;
            }
            throw new Exception("Tài khoản đã bị khóa!");
        }
        throw new Exception("Thông tin đăng nhập không chính xác!");
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

    @Override
    public UserResponse getUserDetail(String token) throws Exception {
        
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn!");
        }

        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        if(!existingUser.isPresent()){
            throw new Exception("Không tìm thấy user với token!");
        }
        return UserResponse.fromUser(existingUser.get());
    }

    @Transactional
    @Override
    public UserResponse updateUserDetail(String token, UpdateUserDTO updateUserDTO) throws Exception {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn!");
        }

        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if(!user.isPresent()){
            throw new Exception("Không tìm thấy user với token!");
        }

        User updateUser = user.get();
        if(updateUserDTO.getAddress() != null){
            updateUser.setAddress(updateUserDTO.getAddress());
        }
        if(updateUserDTO.getFullName() != null){
            updateUser.setFullName(updateUserDTO.getFullName());
        }
        if(updateUserDTO.getEmail() != null){
            updateUser.setEmail(updateUserDTO.getEmail());
        }
        if(updateUserDTO.getDateOfBirth() != null){
            updateUser.setDateOfBirth(updateUserDTO.getDateOfBirth());
        }
        userRepository.save(updateUser);
        return UserResponse.fromUser(updateUser);
    }

    @Transactional
    @Override
    public void updatePassword(String token, UpdatePasswordDTO updatePasswordDTO) throws Exception {

        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn!");
        }

        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if(!user.isPresent()){
            throw new Exception("Không tìm thấy user với token!");
        }

        User existingUser = user.get();

        if(!passwordEncoder.matches(updatePasswordDTO.getPassword(), existingUser.getPassword())){
            throw new Exception("Sai mật khẩu");
        }

        existingUser.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));

        userRepository.save(existingUser);
    }

    @Transactional
    @Override
    public void uploadAvatar(String token, String avatarFileName) throws Exception {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn!");
        }

        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if(!user.isPresent()){
            throw new Exception("Không tìm thấy user với token!");
        }
        User existingUser = user.get();
        existingUser.setAvatar(avatarFileName);
        userRepository.save(existingUser);
    }

    @Override
    public boolean isPresentAvatarFileName(String avatarFileName){
        return userRepository.existsByAvatar(avatarFileName);
    }

}

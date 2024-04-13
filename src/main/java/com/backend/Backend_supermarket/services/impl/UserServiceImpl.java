package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromUser)
                .toList();
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

    @Override
    public UserResponse updateUser(Long id, UserDTO userDTO) throws Exception {

        if(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())){
            throw new Exception("Số điện thoại đã tồn tại !");
        }

        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new Exception("Không tìm thấy "));
        
        existingUser = User.fromUserDTO(userDTO);
        existingUser.setId(id);
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return UserResponse.fromUser(userRepository.save(existingUser));
    }

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

}

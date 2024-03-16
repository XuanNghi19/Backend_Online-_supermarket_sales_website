package com.backend.Backend_supermarket.service;

import com.backend.Backend_supermarket.dto.UserDTO;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setFullName(user.getFullName());
        userDTO.setRole(user.getRole());
        userDTO.setActive((user.getActive()));

        return userDTO;
    }
}

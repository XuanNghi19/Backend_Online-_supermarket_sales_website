package com.backend.Backend_supermarket.service.impl;

import com.backend.Backend_supermarket.repository.UserRepository;
import com.backend.Backend_supermarket.responses.UserResponse;
import com.backend.Backend_supermarket.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }
}

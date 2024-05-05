package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);
    Boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAvatar(String avatar);
}

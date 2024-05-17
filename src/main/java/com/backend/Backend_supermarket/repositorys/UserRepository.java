package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleNot(Role role);
    List<User> findByRole(Role role);
    List<User> findByRoleAndActiveTrueOrderByCreatedAtDesc(Role role);
    Page<User> findByRoleAndActiveTrueOrderByCreatedAtDesc(Role role, Pageable pageable);
    List<User> findByRoleNotAndActiveTrueOrderByCreatedAtDesc(Role role);
    Page<User> findByRoleNotAndActiveTrueOrderByCreatedAtDesc(Role role, Pageable pageable);
    Boolean existsByEmail(String email);


    Optional<User> findByPhoneNumber(String phoneNumber);
    Boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAvatar(String avatar);
}

package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleNot(String role);
    List<User> findByRole(String role);
    List<User> findByRoleAndActiveTrue(String role);
    Page<User> findByRoleAndActiveTrue(String role, Pageable pageable);
    List<User> findByRoleNotAndActiveTrue(String role);
    Page<User> findByRoleNotAndActiveTrue(String role, Pageable pageable);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);

}

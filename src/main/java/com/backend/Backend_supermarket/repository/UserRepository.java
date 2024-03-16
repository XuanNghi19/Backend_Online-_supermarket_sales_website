package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

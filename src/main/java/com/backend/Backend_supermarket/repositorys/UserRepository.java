package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

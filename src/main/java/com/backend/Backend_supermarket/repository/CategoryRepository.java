package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

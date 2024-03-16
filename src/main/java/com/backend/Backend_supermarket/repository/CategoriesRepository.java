package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}

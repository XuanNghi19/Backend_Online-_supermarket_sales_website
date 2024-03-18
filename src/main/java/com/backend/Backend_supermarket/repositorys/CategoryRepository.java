package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);
}

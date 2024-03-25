package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    boolean existsByProductName(String productName);
}

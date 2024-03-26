package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (:keyword IS NULL OR (LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
    "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))) AND (:categoryId = 0 OR p.category.id = :categoryId)")
    Page<Product> findAll(String keyword,Long categoryId, Pageable pageable);

    boolean existsByProductName(String productName);
}

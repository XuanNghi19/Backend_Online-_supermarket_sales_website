package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

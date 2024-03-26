package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long productId);
    Optional<ProductImage> findByImageUrl(String imageUrl);
    void deleteByImageUrl(String imageUrl);
}

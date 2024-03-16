package com.backend.Backend_supermarket.service;

import com.backend.Backend_supermarket.dto.ProductImageDTO;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    // tim cac ProductImage co id cua product
    public List<ProductImageDTO> getProductImagesByProductId(int productId) {
        List<ProductImage> productImages = productImageRepository.findById(productId);
        return productImages.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ProductImageDTO convertEntityToDto(ProductImage productImage) {
        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setProductId(productImage.getProduct().getId());
        productImageDTO.setImageUrl(productImage.getImageUrl());

        return productImageDTO;
    }
}

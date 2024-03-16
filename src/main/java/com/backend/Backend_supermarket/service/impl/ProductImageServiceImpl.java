package com.backend.Backend_supermarket.service.impl;

import com.backend.Backend_supermarket.dto.ProductImageDTO;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.repository.ProductImageRepository;
import com.backend.Backend_supermarket.service.ProductImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    // tim cac ProductImage co id cua product
    @Override
    public List<ProductImageDTO> getProductImagesByProductId(int productId) {
        List<ProductImage> productImages = productImageRepository.findById(productId);
        return productImages.stream()
                .map(ProductImageDTO::fromProductImage)
                .toList();
    }
}

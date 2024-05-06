package com.backend.Backend_supermarket.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.repositorys.ProductImageRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.services.ProductImageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Transactional
    @Override
    public ProductImage createProductImageWithProductId(Long productId, String imageUrl) throws Exception {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new Exception("Không tồn tại sản phẩm với id là : " + productId));
        ProductImage newProductImage = ProductImage.builder()
            .product(product)
            .imageUrl(imageUrl)
            .build();
        return productImageRepository.save(newProductImage);
    }

    @Override
    public ProductImage getProductImageByImageUrl(String imageUrl) throws Exception {
        ProductImage productImage = productImageRepository.findByImageUrl(imageUrl)
            .orElseThrow(() -> new Exception("Không tìm thấy ảnh"));
        return productImage;
    }

    @Transactional
    @Override
    public ProductImage updateProductImageByImageUrl(Long id, String imageUrl) throws Exception {
        ProductImage productImage = productImageRepository.findById(id)
            .orElseThrow(() -> new Exception("Không tìm thấy ảnh"));   
        deleteProductImage(productImage.getImageUrl());
        productImage.setImageUrl(imageUrl);
        return productImageRepository.save(productImage);
    }

    @Override
    public void deleteProductImage(String imageUrl) throws IOException {
        Path uploadDir = Paths.get("uploads");
        // kiểm tra thư mục đã tồn tại chưa
        if (!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), imageUrl);
        Files.deleteIfExists(destination);
        productImageRepository.deleteByImageUrl(imageUrl);
    }

    @Override
    public int getSizeProductImageWithProductId(Long productId) {
        return productImageRepository.findByProductId(productId).size();
    }
}

package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.responses.ProductResponse;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public Page<ProductResponse> getAllProducts(String keyword, Long categoryId, Pageable pageable);
    public ProductResponse getProductById(Long productId) throws Exception;
    public ProductResponse createProduct(ProductDTO productDTO) throws Exception;
    public ProductResponse updateProduct(Long productId,ProductDTO productDTO) throws Exception;
    public void deleteProduct(Long productId);
} 

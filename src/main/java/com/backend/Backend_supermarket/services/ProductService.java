package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.responses.ProductResponse;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAllProduct();
    public List<ProductResponse> getProductsByCategoryId(Long categoryId);
    public ProductResponse getProductById(Long productId) throws Exception;
    public ProductResponse createProduct(ProductDTO productDTO) throws Exception;
    public ProductResponse updateProduct(Long productId,ProductDTO productDTO) throws Exception;
    public void deleteProduct(Long productId);
    public ProductImage createProductImageWithProductId(Long productId, String imageUrl) throws Exception;
} 

package com.backend.Backend_supermarket.service;

import java.util.List;

import com.backend.Backend_supermarket.dto.ProductImageDTO;

public interface ProductImageService{
    public List<ProductImageDTO> getProductImagesByProductId(int productId);
}

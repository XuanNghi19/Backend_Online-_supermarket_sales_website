package com.backend.Backend_supermarket.services;

import java.io.IOException;

import com.backend.Backend_supermarket.models.ProductImage;

public interface ProductImageService {
    public ProductImage createProductImageWithProductId(Long productId, String imageUrl) throws Exception;
    public ProductImage getProductImageByImageUrl(String imageUrl) throws Exception;
    public ProductImage updateProductImageByImageUrl(Long id,String imageUrl) throws Exception;
    public void deleteProductImage(String imageUrl) throws IOException;
    public int getSizeProductImageWithProductId(Long productId);
}

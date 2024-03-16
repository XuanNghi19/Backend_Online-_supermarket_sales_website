package com.backend.Backend_supermarket.service;

import com.backend.Backend_supermarket.dto.ProductDTO;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageService productImageService;

    public List<ProductDTO> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ProductDTO convertEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(product.getCategories().getId());
        productDTO.setDescription((product.getDescription()));
        productDTO.setImageUrl((product.getImageUrl()));

        // lay list ProductImage cung id voi id Product
        productDTO.setListImageUrl(productImageService.getProductImagesByProductId(product.getId()));

        productDTO.setQuantity((product.getQuantity()));

        return productDTO;
    }
}

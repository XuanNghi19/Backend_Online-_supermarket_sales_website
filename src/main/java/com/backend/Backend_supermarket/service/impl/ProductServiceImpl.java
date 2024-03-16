package com.backend.Backend_supermarket.service.impl;

import com.backend.Backend_supermarket.dto.ProductDTO;
import com.backend.Backend_supermarket.repository.ProductRepository;
import com.backend.Backend_supermarket.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepository.findAll()
            .stream()
            .map(ProductDTO::fromProduct)
            .toList();
    }
}

package com.backend.Backend_supermarket.service.impl;

import com.backend.Backend_supermarket.repository.ProductRepository;
import com.backend.Backend_supermarket.responses.ProductResponse;
import com.backend.Backend_supermarket.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
            .stream()
            .map(ProductResponse::fromProduct)
            .toList();
    }
}

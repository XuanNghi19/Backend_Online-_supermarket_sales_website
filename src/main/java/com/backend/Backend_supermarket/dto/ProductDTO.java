package com.backend.Backend_supermarket.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private int id;
    private String productName;
    private float price;
    private int categoryId;
    private String description;
    private String imageUrl;
    private List<ProductImageDTO> listImageUrl;
    private int quantity;
}

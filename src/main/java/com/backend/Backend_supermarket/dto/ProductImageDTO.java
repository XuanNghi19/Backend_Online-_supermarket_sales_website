package com.backend.Backend_supermarket.dto;

import com.backend.Backend_supermarket.models.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductImageDTO {
    private int productId;
    private String imageUrl;
}

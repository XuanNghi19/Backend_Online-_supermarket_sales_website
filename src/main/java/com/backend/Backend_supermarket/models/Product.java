package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ProductDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Float price ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    public static Product fromProduct(ProductDTO productDto, Category category){
        return Product.builder()
            .productName(productDto.getProductName())
            .price(productDto.getPrice())
            .category(category)
            .description(productDto.getDescription())
            .quantity(productDto.getQuantity())
            .build();
    }
}

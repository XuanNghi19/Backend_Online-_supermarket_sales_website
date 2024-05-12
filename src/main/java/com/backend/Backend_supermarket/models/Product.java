package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ProductDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "unit")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sold")
    private int sold;

    public static Product fromProduct(ProductDTO productDto, Category category){
        return Product.builder()
            .productName(productDto.getProductName())
            .price(productDto.getPrice())
            .unit(productDto.getUnit())
            .category(category)
            .description(productDto.getDescription())
            .quantity(productDto.getQuantity())
            .sold(0)
            .build();
    }
}

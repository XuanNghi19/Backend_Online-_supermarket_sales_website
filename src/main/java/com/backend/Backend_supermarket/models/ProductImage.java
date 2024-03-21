package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ProductImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {
    public static final int MAXIMUM_IMAGES = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "image_url")
    private String imageUrl;

    public static ProductImage fromProductImage(ProductImageDTO productImageDto, Product product){
        return ProductImage.builder()
            .product(product)
            .imageUrl(productImageDto.getImageUrl())
            .build();
    }
}

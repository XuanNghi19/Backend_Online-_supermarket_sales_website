package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table(name = "product_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "image_url")
    private String imageUrl;
}

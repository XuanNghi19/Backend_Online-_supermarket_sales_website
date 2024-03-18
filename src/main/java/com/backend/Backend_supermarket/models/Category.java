package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.CategoryDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "image_url")
    private String imageUrl;

    public static Category fromCategoryDTO(CategoryDTO categoryDTO){
        return Category.builder()
            .categoryName(categoryDTO.getCategoryName())
            .imageUrl(categoryDTO.getImageUrl())
            .build();
    }
}

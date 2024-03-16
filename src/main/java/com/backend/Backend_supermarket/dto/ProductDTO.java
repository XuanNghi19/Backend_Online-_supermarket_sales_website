package com.backend.Backend_supermarket.dto;

import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @JsonProperty("product_name")
    private String productName;

    @Min(value = 0, message = "Giá của sản phẩm phải >= 0")
    @JsonProperty("price")
    private float price;

    @NotBlank
    @Min(value = 1)
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @Min(value = 1)
    @JsonProperty("quantity")
    private int quantity;

    public static ProductDTO fromProduct(Product product){
        return ProductDTO.builder()
            .productName(product.getProductName())
            .price(product.getPrice())
            .categoryId(product.getCategory().getId())
            .description(product.getDescription())
            .imageUrl(product.getImageUrl())
            .quantity(product.getQuantity())
            .build();
    }
}

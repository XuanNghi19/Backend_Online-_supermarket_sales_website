package com.backend.Backend_supermarket.responses;

import java.util.List;

import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("price")
    private float price;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private List<String> imageUrl;

    @JsonProperty("quantity")
    private int quantity;

    public static ProductResponse fromProduct(Product product, List<String> productImages){
        return ProductResponse.builder()
            .productName(product.getProductName())
            .price(product.getPrice())
            .categoryId(product.getCategory().getId())
            .description(product.getDescription())
            .imageUrl(productImages)
            .quantity(product.getQuantity())
            .build();
    }
}

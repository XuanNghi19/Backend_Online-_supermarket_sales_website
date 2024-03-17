package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("image_url")
    private String imageUrl;

    public static ProductImageResponse fromProductImage(ProductImage productImage){
        return ProductImageResponse.builder()
            .productId(productImage.getProduct().getId())
            .imageUrl(productImage.getImageUrl())
            .build();
    }
}

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

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image_url")
    private String imageUrl;

    public static ProductImageResponse fromProductImage(ProductImage productImage){
        return ProductImageResponse.builder()
            .id(productImage.getId())
            .imageUrl(productImage.getImageUrl())
            .build();
    }
}

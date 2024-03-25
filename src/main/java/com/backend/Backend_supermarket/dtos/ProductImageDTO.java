package com.backend.Backend_supermarket.dtos;

import com.backend.Backend_supermarket.models.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {

    @NotNull
    @JsonProperty("product_id")
    private Long productId;

    @NotNull
    @JsonProperty("image_url")
    private String imageUrl;

    public static ProductImageDTO fromProductImage(ProductImage productImage){
        return ProductImageDTO.builder()
            .productId(productImage.getProduct().getId())
            .imageUrl(productImage.getImageUrl())
            .build();
    }
}

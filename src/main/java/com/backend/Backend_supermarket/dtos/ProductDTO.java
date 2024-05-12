package com.backend.Backend_supermarket.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @JsonProperty("unit")
    private String unit;

    @NotNull
    @Min(value = 1)
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("description")
    private String description;

    @Min(value = 1)
    @JsonProperty("quantity")
    private int quantity;
}

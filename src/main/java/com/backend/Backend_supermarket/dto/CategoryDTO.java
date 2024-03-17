package com.backend.Backend_supermarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("image_url")
    private String imageUrl;
}

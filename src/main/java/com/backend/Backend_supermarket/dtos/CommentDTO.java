package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDTO {
    @Min(value = 1, message = "Id khách hàng phải lớn hơn hoặc bằng 1")
    @JsonProperty("user_id")
    private Long userId;


    @Min(value = 1, message = "Id sản phẩm phải lớn hơn hoặc bằng 1")
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("content")
    private String content;

    @Min(value = 1, message = "Sao đánh giá tối thiểu là 1")
    @Max(value = 1, message = "Sao đánh giá tối đa là 5")
    @JsonProperty("star")
    private int star;
}

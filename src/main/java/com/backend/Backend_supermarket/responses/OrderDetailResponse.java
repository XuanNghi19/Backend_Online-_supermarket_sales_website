package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.OrderDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class OrderDetailResponse {
    @JsonProperty("product_id")
    private Long productID;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail){
        return OrderDetailResponse.builder()
            .productID(orderDetail.getProduct().getId())
            .numberOfProducts(orderDetail.getNumberOfProducts())
            .totalMoney(orderDetail.getTotalPrice())
            .build();
    }
}

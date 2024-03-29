package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.OrderDetail;
import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class OrderDetailResponse {
    @JsonProperty("product")
    private Product product;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail){
        return OrderDetailResponse.builder()
            .product(orderDetail.getProduct())
            .numberOfProducts(orderDetail.getNumberOfProducts())
            .totalMoney(orderDetail.getTotalPrice())
            .build();
    }
}

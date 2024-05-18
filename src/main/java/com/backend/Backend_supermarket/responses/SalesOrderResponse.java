package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.Order;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class SalesOrderResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private Date shippingDate;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("status")
    private String status;

    @JsonProperty("order_details")
    private List<OrderDetailResponse> orderDetails;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static SalesOrderResponse fromOrder(Order order){
        return SalesOrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .shippingAddress(order.getShippingAddress())
                .shippingDate(order.getShippingDate())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .totalMoney(order.getTotalMoney())
                .build();
    }
}

package com.backend.Backend_supermarket.dtos;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    
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

    @JsonProperty("order_details")
    private List<OrderDetailDTO> orderDetails;
}

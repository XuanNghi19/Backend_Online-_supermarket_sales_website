package com.backend.Backend_supermarket.services;

import java.util.List;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.responses.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    List<OrderResponse> getAllOrderByUserId(Long userId) throws Exception;
    OrderResponse getOrder(Long orderId) throws Exception;
    void deleteOrder(Long orderId);
}

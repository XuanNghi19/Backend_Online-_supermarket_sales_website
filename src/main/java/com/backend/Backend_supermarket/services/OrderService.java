package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.responses.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
}

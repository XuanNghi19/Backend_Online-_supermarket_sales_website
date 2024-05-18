package com.backend.Backend_supermarket.services;

import java.util.List;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.dtos.UpdateOrderDTO;
import com.backend.Backend_supermarket.responses.OrderResponse;
import com.backend.Backend_supermarket.responses.SalesOrderResponse;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws Exception;
    OrderResponse getOrder(Long orderId) throws Exception;
    void deleteOrder(Long orderId);
    List<OrderResponse> getAllOrderByUserToken(String token) throws Exception;

    Page<SalesOrderResponse> getAllOrder(Long userId, int page, int pageSize) throws Exception;
    SalesOrderResponse changeStatusOrder(Long orderId, UpdateOrderDTO updateOrderDTO) throws Exception;
}

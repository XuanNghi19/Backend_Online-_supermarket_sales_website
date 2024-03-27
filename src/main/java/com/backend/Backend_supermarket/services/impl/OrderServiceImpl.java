package com.backend.Backend_supermarket.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.dtos.OrderDetailDTO;
import com.backend.Backend_supermarket.models.Order;
import com.backend.Backend_supermarket.models.OrderDetail;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.OrderDetailRepository;
import com.backend.Backend_supermarket.repositorys.OrderRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.OrderDetailResponse;
import com.backend.Backend_supermarket.responses.OrderResponse;
import com.backend.Backend_supermarket.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    
    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
            .orElseThrow(() -> new Exception("Người dùng không tồn tại"));
        Order order = Order.fromOrderDTO(orderDTO, user);
        orderRepository.save(order);

        OrderResponse response = OrderResponse.fromOrder(order);

        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>(); 
        for(OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()){
            Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Không tồn tại sản phẩm"));
            OrderDetail orderDetail = OrderDetail.builder()
                .product(product)
                .order(order)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalPrice(product.getPrice() * orderDetailDTO.getNumberOfProducts())
                .build();
            orderDetailRepository.save(orderDetail);
            orderDetailResponses.add(OrderDetailResponse.fromOrderDetail(orderDetail));
        }
        response.setOrderDetails(orderDetailResponses);
        return response;
    }
    
}

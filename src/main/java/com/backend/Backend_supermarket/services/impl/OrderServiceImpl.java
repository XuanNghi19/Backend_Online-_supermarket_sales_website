package com.backend.Backend_supermarket.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.backend.Backend_supermarket.dtos.UpdateOrderDTO;
import com.backend.Backend_supermarket.repositorys.*;
import com.backend.Backend_supermarket.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.dtos.OrderDetailDTO;
import com.backend.Backend_supermarket.models.Order;
import com.backend.Backend_supermarket.models.OrderDetail;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.services.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final ProductImageRepository productImageRepository;
    private final CommentRepository commentRepository;
    
    @Transactional
    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
            .orElseThrow(() -> new Exception("Người dùng không tồn tại"));
        Order order = Order.fromOrderDTO(orderDTO, user);
        order.setActive(true);
        order.setStatus("Đang xử lý");
        orderRepository.save(order);

        OrderResponse response = OrderResponse.fromOrder(order);

        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>(); 
        Float totalMoney = 0f;
        for(OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()){
            Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Không tồn tại sản phẩm"));
            if(product.getQuantity() - orderDetailDTO.getNumberOfProducts() < 0){
                throw new Exception("Không đủ sản phẩm");
            }
            OrderDetail orderDetail = OrderDetail.builder()
                .product(product)
                .order(order)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .build();
            totalMoney += product.getPrice() * orderDetailDTO.getNumberOfProducts();
            orderDetailRepository.save(orderDetail);
            product.setQuantity(product.getQuantity() - orderDetailDTO.getNumberOfProducts());
            product.setSold(orderDetailDTO.getNumberOfProducts());
            productRepository.save(product);
            ProductResponse productResponse = getProductWithImagesAndComment(product);
            orderDetailResponses.add(OrderDetailResponse.fromOrderDetail(orderDetail, productResponse));
        }
        response.setOrderDetails(orderDetailResponses);
        response.setTotalMoney(totalMoney);
        order.setTotalMoney(totalMoney);
        orderRepository.save(order);
        return response;
    }

    @Override
    public List<OrderResponse> getAllOrderByUserToken(String token) throws Exception {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token đã hết hạn!");
        }

        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        if(!existingUser.isPresent()){
            throw new Exception("Không tìm thấy user với token!");
        }
        List<OrderResponse> responses = orderRepository.getAllOrderByUserId(existingUser.get().getId())
            .stream()
            .map(order -> loadOrderResponse(order))
            .toList();
        return responses;
    }

    @Override
    public Page<SalesOrderResponse> getAllOrder(Long userId, int page, int pageSize) throws Exception {
        return orderRepository.findAllByUserId(
                userId,
                PageRequest.of(page - 1, pageSize)
        ).map(SalesOrderResponse::fromOrder);
    }

    @Override
    public SalesOrderResponse changeStatusOrder(Long orderId, UpdateOrderDTO updateOrderDTO) throws Exception {
        Order updateOrder = orderRepository.findById(orderId).orElseThrow(()-> new Exception("Khong tim thay order voi id: " + orderId));
        if(!Objects.equals(updateOrderDTO.getPaymentStatus(), "")) updateOrder.setPaymentStatus(updateOrderDTO.getPaymentStatus());
        if(!Objects.equals(updateOrderDTO.getStatus(), "")) updateOrder.setStatus(updateOrderDTO.getStatus());

        return SalesOrderResponse.fromOrder(orderRepository.save(updateOrder));
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            order.get().setActive(false);
            orderRepository.save(order.get());
        }
    } 

    @Override
    public OrderResponse getOrder(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
            .map(order -> loadOrderResponse(order))
            .orElseThrow(() -> new Exception("Không tìm thấy Order với id: " + orderId));
    }
 
    private OrderResponse loadOrderResponse(Order order){
        OrderResponse response = OrderResponse.fromOrder(order);
        List<OrderDetailResponse> orderDetailResponses = orderDetailRepository.findByOrderId(order.getId())
            .stream()
            .map(orderDetail ->{
                return OrderDetailResponse.fromOrderDetail(orderDetail, getProductWithImagesAndComment(orderDetail.getProduct()));
            })
            .toList();
        response.setOrderDetails(orderDetailResponses);
        return response;
    }

    private ProductResponse getProductWithImagesAndComment(Product product){
        List<ProductImageResponse> productImages = productImageRepository.findByProductId(product.getId())
                .stream()
                .map(ProductImageResponse::fromProductImage)
                .toList();
        List<CommentResponse> comments = commentRepository.findByProductId(product.getId())
                .stream()
                .map(CommentResponse::fromComment)
                .toList();
        return ProductResponse.fromProduct(product, productImages,comments);
    }
}

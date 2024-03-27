package com.backend.Backend_supermarket.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.responses.OrderResponse;
import com.backend.Backend_supermarket.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(
        @RequestBody @Valid OrderDTO orderDto
    ){
        try {

            OrderResponse response = orderService.createOrder(orderDto);
            return ResponseEntity.ok().body(response);
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

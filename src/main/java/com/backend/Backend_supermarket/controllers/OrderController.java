package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOrdersWithUserId(
        @PathVariable("id") Long userId
    ){
        try {
            List<OrderResponse> responses = orderService.getAllOrderByUserId(userId);
            return ResponseEntity.ok().body(responses);
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(
        @PathVariable("id") Long id
    ){
        try {
            OrderResponse responses = orderService.getOrder(id);
            return ResponseEntity.ok().body(responses);
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(
        @PathVariable("id") Long id
    ){
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).build();
            
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.backend.Backend_supermarket.controllers;

import java.util.List;

import com.backend.Backend_supermarket.dtos.UpdateOrderDTO;
import com.backend.Backend_supermarket.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.backend.Backend_supermarket.dtos.OrderDTO;
import com.backend.Backend_supermarket.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseData<?> createOrder(
        @RequestBody @Valid OrderDTO orderDto,
        BindingResult result
    ){
        try {
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
            }
            OrderResponse response = orderService.createOrder(orderDto);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo đơn hàng thành công", response);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseData<?> getOrdersWithUser(
        @RequestHeader("Authorization") String authorizationHeader
    ){
        try {
            String token = authorizationHeader.substring(7);
            List<OrderResponse> responses = orderService.getAllOrderByUserToken(token);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách đơn hàng thành công", responses);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOrder(
        @PathVariable("id") Long id
    ){
        try {
            OrderResponse responses = orderService.getOrder(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy đơn hàng thành công", responses);
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOrder(
        @PathVariable("id") Long id
    ){
        try {
            orderService.deleteOrder(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Xóa đơn hàng thành công");
            
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrder(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ){
        try {
            Page<SalesOrderResponse> salesOrderResponses = orderService.getAllOrder(userId, page, pageSize);
            return ResponseEntity.ok().body(salesOrderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> changeStatusOrder(
            @PathVariable("id") Long orderId,
            @RequestBody @Valid UpdateOrderDTO updateOrderDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages.toString());
            }
            SalesOrderResponse response = orderService.changeStatusOrder(orderId, updateOrderDTO);
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}

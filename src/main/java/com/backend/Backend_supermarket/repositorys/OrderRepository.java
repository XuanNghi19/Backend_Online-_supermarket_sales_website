package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllOrderByUserId(Long userId);
}

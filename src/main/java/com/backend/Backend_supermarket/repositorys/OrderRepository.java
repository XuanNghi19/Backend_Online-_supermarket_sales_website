package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

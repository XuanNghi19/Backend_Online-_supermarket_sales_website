package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}

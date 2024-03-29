package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Long id);
}

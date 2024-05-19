package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.createdAt DESC")
    List<Order> getAllOrderByUserId(@Param("userId") Long userId);

    @Query("SELECT o FROM Order o WHERE (:userId IS NULL OR o.user.id = :userId) AND o.active = true ORDER BY o.createdAt DESC")
    Page<Order> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}

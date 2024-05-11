package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Query("SELECT r FROM Receipt r WHERE (" +
            "name IS NULL OR r.name LIKE %:name%)" +
            "AND (:status IS NULL OR r.status LIKE %:status%)" +
            "AND (:startDate IS NULL OR r.createdAt >= :startDate)" +
            "AND (:endDate IS NULL OR r.createdAt <= :endDate)")
    List<Receipt> findByNameAndStatusAndCreateAtBetween (
            @Param("name") String name,
            @Param("status") String status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}

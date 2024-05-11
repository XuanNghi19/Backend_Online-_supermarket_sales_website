package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.ReceiptDetail;
import com.backend.Backend_supermarket.responses.ReceiptDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    public List<ReceiptDetail> findByReceiptId(Long receiptId);
    @Modifying
    @Query("DELETE FROM ReceiptDetail rd WHERE rd.receipt.id = :receiptId")
    public void deleteAllByReceiptId(Long receiptId);
}

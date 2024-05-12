package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.InventoryCheckDetail;
import com.backend.Backend_supermarket.models.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryCheckDetailRepository extends JpaRepository<InventoryCheckDetail, Long> {
    public List<InventoryCheckDetail> findByInventoryCheckId(Long inventoryCheckId);
    @Modifying
    @Query("DELETE FROM InventoryCheckDetail rd WHERE rd.inventoryCheck.id = :inventoryCheckId")
    public void deleteAllByInventoryCheckId(Long inventoryCheckId);
}

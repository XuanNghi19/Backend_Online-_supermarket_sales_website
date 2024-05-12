package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.InventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.responses.InventoryCheckResponse;
import com.backend.Backend_supermarket.responses.ReceiptResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryCheckService {
    public List<InventoryCheckResponse> getAllInventoryCheck();
    public List<InventoryCheckResponse> searchInventoryCheck(String name, LocalDateTime startDate, LocalDateTime endDate, String status) throws Exception;
    public InventoryCheckResponse getInventoryCheckResponseInformation(Long inventoryCheckId);
    public InventoryCheckResponse createInventoryCheck(InventoryCheckDTO inventoryCheckDTO) throws Exception;
    public InventoryCheckResponse updateInventoryCheck(UpdateInventoryCheckDTO updateInventoryCheckDTO) throws Exception;
    public void deleteInventoryCheck(Long inventoryCheckId) throws Exception;
    public InventoryCheckResponse productVerifications(UpdateInventoryCheckDTO updateInventoryCheckDTO) throws Exception;
}

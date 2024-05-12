package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDetailDTO;
import com.backend.Backend_supermarket.models.InventoryCheckDetail;
import com.backend.Backend_supermarket.responses.InventoryCheckDetailResponse;

import java.util.List;

public interface InventoryCheckDetailService {
    public List<InventoryCheckDetailResponse> getAllInventoryCheckDetailResponseByInventoryCheckId(Long inventoryCheckId);
    public List<InventoryCheckDetail> getAllInventoryCheckDetailInventoryCheckId(Long inventoryCheckId);
    public InventoryCheckDetailResponse createInventoryCheckDetail(InventoryCheckDetailDTO inventoryCheckDetailDTO, Long inventoryCheckId) throws Exception;
    public InventoryCheckDetailResponse createInventoryCheckDetail(UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO, Long inventoryCheckId) throws Exception;
    public InventoryCheckDetailResponse updateInventoryCheckDetail(UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO) throws Exception;
    public void productVerification(Long productId, int quantity) throws Exception;
    public void deleteInventoryCheckDetail(Long inventoryCheckDetailId) throws Exception;
    public void deleteAllByInventoryCheckId(Long inventoryCheckId) throws Exception;
}

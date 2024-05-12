package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDetailDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.InventoryCheckDetail;
import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryCheckDetailResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("inventory_check_id")
    private Long inventoryCheckId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("actual_inventory")
    private int actualInventory;

    @JsonProperty("reason_missing")
    private String reasonMissing;

    @Column(name = "note")
    private String note;

    public static InventoryCheckDetailResponse fromInventoryCheckDetail(
            InventoryCheckDetail inventoryCheckDetail
    ) {
        return InventoryCheckDetailResponse.builder()
                .id(inventoryCheckDetail.getId())
                .inventoryCheckId(inventoryCheckDetail.getInventoryCheck().getId())
                .productId(inventoryCheckDetail.getProduct().getId())
                .actualInventory(inventoryCheckDetail.getActualInventory())
                .reasonMissing(inventoryCheckDetail.getReasonMissing())
                .note(inventoryCheckDetail.getNote())
                .build();
    }

    public static InventoryCheckDetailResponse fromUpdateInventoryCheckDetailDTO(
            UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO

    ) {
        return InventoryCheckDetailResponse.builder()
                .id(updateInventoryCheckDetailDTO.getId())
                .inventoryCheckId(updateInventoryCheckDetailDTO.getInventoryCheckId())
                .productId(updateInventoryCheckDetailDTO.getProductId())
                .actualInventory(updateInventoryCheckDetailDTO.getActualInventory())
                .reasonMissing(updateInventoryCheckDetailDTO.getReasonMissing())
                .note(updateInventoryCheckDetailDTO.getNote())
                .build();
    }
}

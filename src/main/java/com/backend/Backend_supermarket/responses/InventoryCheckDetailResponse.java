package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
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

    @JsonProperty("inventory_check")
    private InventoryCheck inventoryCheck;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("actual_inventory")
    private int actualInventory;

    @JsonProperty("systemInventory")
    private int systemInventory = product.getQuantity();

    @JsonProperty("different")
    private int different = actualInventory - systemInventory;

    @JsonProperty("reason_missing")
    private String reasonMissing;

    @Column(name = "note")
    private String note;

    public static InventoryCheckDetailResponse fromInventoryCheckDetail(
            InventoryCheckDetail inventoryCheckDetail,
            InventoryCheck inventoryCheck,
            Product product
    ) {
        return InventoryCheckDetailResponse.builder()
                .id(inventoryCheckDetail.getId())
                .inventoryCheck(inventoryCheck)
                .product(product)
                .unit(inventoryCheckDetail.getUnit())
                .actualInventory(inventoryCheckDetail.getActualInventory())
                .reasonMissing(inventoryCheckDetail.getReasonMissing())
                .note(inventoryCheckDetail.getNote())
                .build();
    }
}

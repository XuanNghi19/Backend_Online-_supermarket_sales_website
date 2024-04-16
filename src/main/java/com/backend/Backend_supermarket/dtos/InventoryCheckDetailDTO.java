package com.backend.Backend_supermarket.dtos;

import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryCheckDetailDTO {

    @JsonProperty("inventory_check_id")
    private InventoryCheck inventoryCheckId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("actual_inventory")
    private int actualInventory;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("reason_missing")
    private String reasonMissing;

    @JsonProperty("note")
    private String note;
}

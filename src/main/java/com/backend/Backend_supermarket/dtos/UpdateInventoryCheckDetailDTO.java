package com.backend.Backend_supermarket.dtos;

import com.backend.Backend_supermarket.models.InventoryCheck;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class UpdateInventoryCheckDetailDTO {
    @JsonProperty("id")
    private Long id;

    /**
     * none
     * create
     * update
     * delete
     */
    @JsonProperty("status")
    private String status;

    @JsonProperty("inventory_check_id")
    private Long inventoryCheckId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("actual_inventory")
    private int actualInventory;

    @JsonProperty("reason_missing")
    private String reasonMissing;

    @JsonProperty("note")
    private String note;
}

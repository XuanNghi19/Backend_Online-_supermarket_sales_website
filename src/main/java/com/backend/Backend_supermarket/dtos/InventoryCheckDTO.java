package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryCheckDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("note")
    private String note;

    @JsonProperty("inventory_check_details")
    private List<InventoryCheckDetailDTO> inventoryCheckDetailDTOS;
}

package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryCheckResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private User user;

    @JsonProperty("name")
    private String name;

    /*
     * Hủy
     * Chưa hoàn tất
     * Hoàn tất
     * Đã cân bằng
     * */
    @JsonProperty("status")
    private String status;

    @JsonProperty("note")
    private String note;

    @JsonProperty("inventory_check_details")
    private List<InventoryCheckDetailResponse> inventoryCheckDetailResponses;

    public static InventoryCheckResponse fromInventoryCheck(
            InventoryCheck inventoryCheck,
            User user
    ) {
        return InventoryCheckResponse.builder()
                .id(inventoryCheck.getId())
                .user(user)
                .name(inventoryCheck.getName())
                .status(inventoryCheck.getStatus())
                .note(inventoryCheck.getNote())
                .build();
    }
}

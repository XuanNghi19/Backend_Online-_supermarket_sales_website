package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class InventoryCheckResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private UserResponse userResponse;

    @JsonProperty("name")
    private String name;

    /**
     * Đã tạo
     * Đang nhập dữ liêu
     * Đã hoàn thành nhập dữ liệu
     * Đã cân bằng
     * Hủy bỏ
     */
    @JsonProperty("status")
    private String status;

    @JsonProperty("verification_date")
    private Date verificationDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("inventory_check_details")
    private List<InventoryCheckDetailResponse> inventoryCheckDetailResponses;

    public static InventoryCheckResponse fromInventoryCheck(
            InventoryCheck inventoryCheck
    ) {
        return InventoryCheckResponse.builder()
                .id(inventoryCheck.getId())
                .userResponse(UserResponse.fromUser(inventoryCheck.getUser()))
                .name(inventoryCheck.getName())
                .status(inventoryCheck.getStatus())
                .verificationDate(inventoryCheck.getVerificationDate())
                .note(inventoryCheck.getNote())
                .build();
    }
}

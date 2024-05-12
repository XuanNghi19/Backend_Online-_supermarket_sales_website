package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateInventoryCheckDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

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

    @JsonProperty("note")
    private String note;

    @JsonProperty("inventory_check_details")
    private List<UpdateInventoryCheckDetailDTO> updateInventoryCheckDetailDTOS;
}

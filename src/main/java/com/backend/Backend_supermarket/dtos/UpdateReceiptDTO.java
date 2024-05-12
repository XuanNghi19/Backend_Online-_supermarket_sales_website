package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReceiptDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("partner_id")
    private Long partnerId;

    @JsonProperty("user_id")
    private Long userId;

    /*
     * Đã tạo
     * Đang nhập hàng
     * Đã nhập kho
     * Đã hoàn tất
     * Hủy bỏ
     * */
    @JsonProperty("status")
    private String status;

    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("amount_paid")
    private Float amountPaid;

    @JsonProperty("note")
    private String note;

    @JsonProperty("UpdateReceiptDetailDTOS")
    private List<UpdateReceiptDetailDTO> updateReceiptDetailDTOS;
}

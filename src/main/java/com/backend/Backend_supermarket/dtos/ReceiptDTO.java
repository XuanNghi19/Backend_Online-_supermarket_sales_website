package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("partner_id")
    private Long partnerId;

    @JsonProperty("user_id")
    private Long userId;

    /*
    * Hủy bỏ
    * Đang nhập hàng
    * Đang chờ thanh toán
    * Còn nợ
    * Đã thanh toán
    * Hoàn tất
    * */
    @JsonProperty("status")
    private String status;

    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;

    @JsonProperty("amount_paid")
    private Double amountPaid;

    @JsonProperty("note")
    private String note;

    @JsonProperty("ReceiptDetail")
    private List<ReceiptDTO> receiptDTOS;

}

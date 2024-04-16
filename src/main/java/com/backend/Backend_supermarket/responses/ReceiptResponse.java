package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.models.Partner;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReceiptResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("partner")
    private Partner partner;

    @JsonProperty("user")
    private User user;

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

    @JsonProperty("total_money")
    private Double totalMoney;

    @JsonProperty("amount_paid")
    private Double amountPaid;

    @JsonProperty("owe")
    private Double owe = totalMoney - amountPaid;

    @JsonProperty("note")
    private String note;

    @JsonProperty("ReceiptDetail")
    private List<ReceiptDetailResponse> receiptDetailResponses;

    public static ReceiptResponse fromReceipt(
            Receipt receipt,
            Partner partner,
            User user
    ) {
        return ReceiptResponse.builder()
                .id(receipt.getId())
                .name(receipt.getName())
                .partner(partner)
                .user(user)
                .status(receipt.getStatus())
                .deliveryDate(receipt.getDeliveryDate())
                .amountPaid(receipt.getAmountPaid())
                .note(receipt.getNote())
                .build();
    }
}

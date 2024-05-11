package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.models.Partner;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.models.ReceiptDetail;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.services.ReceiptDetailService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    private PartnerResponse partnerResponse;

    @JsonProperty("user")
    private UserResponse userResponse;

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

    @JsonProperty("owe")
    private Float owe;

    @JsonProperty("note")
    private String note;

    @JsonProperty("ReceiptDetail")
    private List<ReceiptDetailResponse> receiptDetailResponses;

    public static ReceiptResponse fromReceipt(
            Receipt receipt
    ) {
        return ReceiptResponse.builder()
                .id(receipt.getId())
                .name(receipt.getName())
                .partnerResponse(PartnerResponse.fromPartner(receipt.getPartner()))
                .userResponse(UserResponse.fromUser(receipt.getUser()))
                .status(receipt.getStatus())
                .deliveryDate(receipt.getDeliveryDate())
                .totalMoney(receipt.getTotalMoney())
                .amountPaid(receipt.getAmountPaid())
                .owe(receipt.getTotalMoney() - receipt.getAmountPaid())
                .note(receipt.getNote())
                .build();
    }
}

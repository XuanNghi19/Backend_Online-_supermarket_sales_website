package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.models.ReceiptDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptDetailResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("receipt_id")
    private Long receiptId;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("cost_of_product")
    private Float costOfProduct;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("unit_price")
    private Float unitPrice;

    public static ReceiptDetailResponse fromReceiptDetail(
            ReceiptDetail receiptDetail
    ) {
        return ReceiptDetailResponse.builder()
                .id(receiptDetail.getId())
                .receiptId(receiptDetail.getReceipt().getId())
                .product(receiptDetail.getProduct())
                .costOfProduct(receiptDetail.getCostOfProduct())
                .quantity(receiptDetail.getQuantity())
                .unitPrice(receiptDetail.getProduct().getPrice() * receiptDetail.getQuantity())
                .build();
    }
}

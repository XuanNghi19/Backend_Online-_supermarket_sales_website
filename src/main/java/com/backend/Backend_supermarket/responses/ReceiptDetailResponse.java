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

    @JsonProperty("receipt")
    private Receipt receipt;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("unit_price")
    private Double unitPrice = Double.valueOf(product.getPrice()) * quantity;

    public static ReceiptDetailResponse fromReceiptDetail(
            ReceiptDetail receiptDetail,
            Receipt receipt,
            Product product
    ) {
        return ReceiptDetailResponse.builder()
                .id(receiptDetail.getId())
                .receipt(receipt)
                .product(product)
                .quantity(receiptDetail.getQuantity())
                .build();
    }
}

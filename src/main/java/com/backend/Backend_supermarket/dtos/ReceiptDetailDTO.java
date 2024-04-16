package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptDetailDTO {
    @JsonProperty("receipt_id")
    private Long receiptId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private Long quantity;
}

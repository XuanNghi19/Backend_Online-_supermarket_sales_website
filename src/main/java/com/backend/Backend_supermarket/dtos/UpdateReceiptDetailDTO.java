package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReceiptDetailDTO {
    @JsonProperty("id")
    private Long id;
    /**
     * none
     * create
     * update
     * delete
     */
    @JsonProperty("status")
    private String status;

    @JsonProperty("receipt_id")
    private Long receiptId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("cost_of_product")
    private Float costOfProduct;

    @JsonProperty("quantity")
    private int quantity;
}

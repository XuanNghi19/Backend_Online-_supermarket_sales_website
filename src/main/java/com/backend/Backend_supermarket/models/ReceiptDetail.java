package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.ReceiptDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receipt_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private Long quantity;

    public static ReceiptDetail fromReceiptDetailDTO(
            ReceiptDetailDTO receiptDetailDTO,
            Receipt receipt,
            Product product
    ) {
        return ReceiptDetail.builder()
                .receipt(receipt)
                .product(product)
                .unit(receiptDetailDTO.getUnit())
                .quantity(receiptDetailDTO.getQuantity())
                .build();
    }
}

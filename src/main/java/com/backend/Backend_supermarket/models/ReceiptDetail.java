package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.ReceiptDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDetailDTO;
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

    @Column(name = "cost_of_product")
    private Float costOfProduct;

    @Column(name = "quantity")
    private int quantity;

    public static ReceiptDetail fromReceiptDetailDTO(
            ReceiptDetailDTO receiptDetailDTO,
            Receipt receipt,
            Product product
    ) {
        return ReceiptDetail.builder()
                .receipt(receipt)
                .product(product)
                .costOfProduct(receiptDetailDTO.getCostOfProduct())
                .quantity(receiptDetailDTO.getQuantity())
                .build();
    }

    public static ReceiptDetail fromUpdateReceiptDetailDTO(
            UpdateReceiptDetailDTO updateReceiptDetailDTO,
            Receipt receipt,
            Product product
    ) {
        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setId(updateReceiptDetailDTO.getId());
        receiptDetail.setReceipt(receipt);
        receiptDetail.setProduct(product);
        receiptDetail.setCostOfProduct(updateReceiptDetailDTO.getCostOfProduct());
        receiptDetail.setQuantity(updateReceiptDetailDTO.getQuantity());
        return receiptDetail;
    }
}

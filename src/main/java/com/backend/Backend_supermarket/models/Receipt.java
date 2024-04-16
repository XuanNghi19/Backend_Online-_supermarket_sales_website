package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * Hủy bỏ
     * Đang nhập hàng
     * Đang chờ thanh toán
     * Còn nợ
     * Đã thanh toán
     * Hoàn tất
     * */
    @Column(name = "status")
    private String status;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "note")
    private String note;

    public static Receipt fromReceiptDTO(
            ReceiptDTO receiptDTO,
            Partner partner,
            User user
    ) {
        return Receipt.builder()
                .name(receiptDTO.getName())
                .partner(partner)
                .user(user)
                .status(receiptDTO.getStatus())
                .deliveryDate(receiptDTO.getDeliveryDate())
                .amountPaid(receiptDTO.getAmountPaid())
                .note(receiptDTO.getNote())
                .build();
    }
}

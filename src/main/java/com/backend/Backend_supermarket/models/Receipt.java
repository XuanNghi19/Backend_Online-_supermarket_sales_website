package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt extends BaseEntity{
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
     * Đã tạo
     * Đang nhập hàng
     * Đã nhập kho
     * Đã hoàn tất
     * Hủy bỏ
     * */
    @Column(name = "status")
    private String status;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "amount_paid")
    private Float amountPaid;

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
                .totalMoney(receiptDTO.getTotalMoney())
                .amountPaid(receiptDTO.getAmountPaid())
                .note(receiptDTO.getNote())
                .build();
    }

    public static Receipt fromUpdateReceiptDTO(
            Receipt receipt,
            UpdateReceiptDTO updateReceiptDTO,
            Partner partner,
            User user
    ) {
        receipt.setId(updateReceiptDTO.getId());
        receipt.setName(updateReceiptDTO.getName());
        receipt.setPartner(partner);
        receipt.setUser(user);
        receipt.setStatus(updateReceiptDTO.getStatus());
        receipt.setDeliveryDate(updateReceiptDTO.getDeliveryDate());
        receipt.setTotalMoney(updateReceiptDTO.getTotalMoney());
        receipt.setAmountPaid(updateReceiptDTO.getAmountPaid());
        receipt.setNote(updateReceiptDTO.getNote());
        return receipt;
    }
}

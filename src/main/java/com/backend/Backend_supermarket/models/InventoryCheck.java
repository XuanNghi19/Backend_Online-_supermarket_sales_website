package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.InventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDTO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventory_checks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCheck extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    /**
     * Đã tạo
     * Đang nhập dữ liêu
     * Đã hoàn thành nhập dữ liệu
     * Đã cân bằng
     * Hủy bỏ
     */
    @Column(name = "status")
    private String status;

    @Column(name = "verification_date")
    private Date verificationDate;

    @Column(name = "note")
    private String note;

    public static InventoryCheck fromInventoryCheckDTO(InventoryCheckDTO inventoryCheckDTO, User user) {
        return InventoryCheck.builder()
                .user(user)
                .name(inventoryCheckDTO.getName())
                .status(inventoryCheckDTO.getStatus())
                .note(inventoryCheckDTO.getNote())
                .build();
    }
    public static InventoryCheck fromUpdateInventoryCheckDTO(
            InventoryCheck inventoryCheck,
            UpdateInventoryCheckDTO updateInventoryCheckDTO,
            User user
    ) {
        inventoryCheck.setId(updateInventoryCheckDTO.getId());
        inventoryCheck.setUser(user);
        inventoryCheck.setName(updateInventoryCheckDTO.getName());
        inventoryCheck.setStatus(updateInventoryCheckDTO.getStatus());
        inventoryCheck.setNote(updateInventoryCheckDTO.getNote());
        return inventoryCheck;
    }
}

package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity
@Table(name = "inventory_check_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryCheckDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_check_id")
    private InventoryCheck inventoryCheck;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "unit")
    private String unit;

    @Column(name = "actual_inventory")
    private int actualInventory;

    @Column(name = "reason_missing")
    private String reasonMissing;

    @Column(name = "note")
    private String note;

    public static InventoryCheckDetail fromInventoryCheckDetailDTO(
            InventoryCheckDetailDTO inventoryCheckDetailDTO,
            InventoryCheck inventoryCheck,
            Product product
    ) {
        return InventoryCheckDetail.builder()
                .inventoryCheck(inventoryCheck)
                .product(product)
                .unit(inventoryCheckDetailDTO.getUnit())
                .actualInventory(inventoryCheckDetailDTO.getActualInventory())
                .reasonMissing(inventoryCheckDetailDTO.getReasonMissing())
                .note(inventoryCheckDetailDTO.getNote())
                .build();
    }
}

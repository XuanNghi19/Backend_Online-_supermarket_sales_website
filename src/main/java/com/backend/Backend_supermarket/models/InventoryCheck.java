package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.InventoryCheckDTO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

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

    @Column(name = "status")
    private String status;

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
}

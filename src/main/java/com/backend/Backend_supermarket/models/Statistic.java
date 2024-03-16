package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "statistics")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Statistic extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "status")
    private String status;


}

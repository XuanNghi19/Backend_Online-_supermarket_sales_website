package com.backend.Backend_supermarket.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "comment")
    private String comment;

    @Column(name = "star")
    private int star;
}

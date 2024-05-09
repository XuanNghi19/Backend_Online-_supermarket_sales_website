package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.CommentDTO;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private int star;

    public static Comment fromCommentDTO(CommentDTO commentDTO, User user, Product product){
        return Comment.builder()
            .user(user)
            .product(product)
            .content(commentDTO.getContent())
            .star(commentDTO.getStar())
            .build();
    }
}

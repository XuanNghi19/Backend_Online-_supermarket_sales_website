package com.backend.Backend_supermarket.responses;

import java.time.LocalDateTime;

import com.backend.Backend_supermarket.models.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class CommentResponse {
    @JsonProperty("username")
    private String username;

    @JsonProperty("content")
    private String content;

    @JsonProperty("star")
    private int star;

    @JsonProperty("create_at")
    private LocalDateTime createAt;

    public static CommentResponse fromComment(Comment comment){
        return CommentResponse.builder()
            .username(comment.getUser().getUsername())
            .content(comment.getContent())
            .star(comment.getStar())
            .createAt(comment.getCreatedAt())
            .build();
    }
}

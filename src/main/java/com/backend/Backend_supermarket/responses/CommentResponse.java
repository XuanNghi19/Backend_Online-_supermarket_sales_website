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

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static CommentResponse fromComment(Comment comment){
        return CommentResponse.builder()
            .username(formatUsername(comment.getUser().getFullName()))
            .content(comment.getContent())
            .star(comment.getStar())
            .createdAt(comment.getCreatedAt())
            .build();
    }

    private static String formatUsername(String name){
        String tmp = "";
        String[] words = name.split("\\s++");
        for(int j = 0; j < words.length - 1; j ++){
            tmp += words[j].charAt(0);
            for(int i = 1; i < words[j].length(); i++){
                tmp += "*";
            }
            tmp += " ";
        }
        tmp += words[words.length - 1];
        return tmp;
    }
}

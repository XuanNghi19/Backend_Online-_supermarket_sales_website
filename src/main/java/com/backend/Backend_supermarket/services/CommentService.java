package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.CommentDTO;
import com.backend.Backend_supermarket.responses.CommentResponse;

public interface CommentService {
    CommentResponse createComment(CommentDTO commentDTO) throws Exception;
    CommentResponse updateComment(Long id,CommentDTO commentDTO, String token) throws Exception;
    void deleteComment(Long id);
}

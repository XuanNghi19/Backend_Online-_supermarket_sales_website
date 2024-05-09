package com.backend.Backend_supermarket.services.impl;

import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.dtos.CommentDTO;
import com.backend.Backend_supermarket.models.Comment;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.CommentRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.repositorys.UserRepository;
import com.backend.Backend_supermarket.responses.CommentResponse;
import com.backend.Backend_supermarket.services.CommentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public CommentResponse createComment(CommentDTO commentDTO) throws Exception {
        User user = userRepository.findById(commentDTO.getUserId())
            .orElseThrow(() -> new Exception("Không tìm thấy user"));
        Product product = productRepository.findById(commentDTO.getProductId())
            .orElseThrow(() -> new Exception("Không tìm thấy product"));
        Comment comment = Comment.fromCommentDTO(commentDTO, user, product);
        commentRepository.save(comment);
        return CommentResponse.fromComment(comment);
    }

    @Transactional
    @Override
    public CommentResponse updateComment(Long id, CommentDTO commentDTO) throws Exception {
        Comment existingComment = commentRepository.findById(id)
            .orElseThrow(() -> new Exception("Không tìm thấy comment"));
        existingComment.setContent(commentDTO.getContent());
        commentRepository.save(existingComment);
        return CommentResponse.fromComment(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
}

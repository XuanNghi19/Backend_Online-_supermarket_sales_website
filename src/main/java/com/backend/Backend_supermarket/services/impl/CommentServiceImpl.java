package com.backend.Backend_supermarket.services.impl;

import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
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
    private final JwtTokenUtils jwtTokenUtils;

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
    public CommentResponse updateComment(Long id, CommentDTO commentDTO, String token) throws Exception {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new Exception("Token hết hạn sử dụng!");
        }
        String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

        User user = userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new Exception("Không tìm thấy với token"));

        Comment existingComment = commentRepository.findById(id)
            .orElseThrow(() -> new Exception("Không tìm thấy comment"));

        if(existingComment.getUser().getId() != user.getId()){
            throw new Exception("Chỉ chủ sở hữu mới có thể thay đổi nội dung comment!");
        }
        existingComment.setContent(commentDTO.getContent());
        existingComment.setStar(commentDTO.getStar());
        commentRepository.save(existingComment);
        return CommentResponse.fromComment(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
}

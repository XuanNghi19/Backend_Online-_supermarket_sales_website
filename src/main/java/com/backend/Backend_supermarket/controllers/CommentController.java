package com.backend.Backend_supermarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.CommentDTO;
import com.backend.Backend_supermarket.responses.CommentResponse;
import com.backend.Backend_supermarket.responses.ResponseData;
import com.backend.Backend_supermarket.services.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseData<?> createComment(
        @RequestBody @Valid CommentDTO commentDTO){
        try {
            CommentResponse responses = commentService.createComment(commentDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách đơn hàng thành công", responses);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}

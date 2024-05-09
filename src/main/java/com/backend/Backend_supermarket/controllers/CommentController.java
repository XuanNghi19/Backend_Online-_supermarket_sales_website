package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.CommentDTO;
import com.backend.Backend_supermarket.responses.CommentResponse;
import com.backend.Backend_supermarket.responses.ResponseData;
import com.backend.Backend_supermarket.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseData<?> createComment(
        @RequestBody @Valid CommentDTO commentDTO,
        BindingResult result){
        try {
            if (result.hasErrors()) {
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
                // return ResponseEntity.badRequest().body(errorMessages);
            }
            CommentResponse responses = commentService.createComment(commentDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Tạo comment thành công", responses);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateComment(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable("id") Long id,
        @RequestBody @Valid CommentDTO commentDTO){
        try {
            String token = authorizationHeader.substring(7);
            CommentResponse responses = commentService.updateComment(id,commentDTO, token);
            return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật comment thành công", responses);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteComment(
        @PathVariable("id") Long id){
        try {
            commentService.deleteComment(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Xóa comment thành công");
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}

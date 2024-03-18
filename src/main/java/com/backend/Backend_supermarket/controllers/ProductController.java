package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Backend_supermarket.dtos.ProductDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @PostMapping()
    public ResponseEntity<?> createProduct(
        @RequestBody @Valid ProductDTO productDTO,
        BindingResult result
    ){
        try {
            // lấy ra danh sách lỗi check được ở validation
            if(result.hasErrors()){
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok().body("Tạo sản phẩm thành công : " + productDTO);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/uploads/{id}", 
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProductImage(
        @PathVariable("id") Long productId,
        @ModelAttribute("files") List<MultipartFile> files
    ) {
        try {

            List<String> images = files.stream()
                .map(
                    file -> {
                        return file.getOriginalFilename();
                    }
                )
                .toList();
            return ResponseEntity.ok().body("Tạo ảnh cho sản phẩm với id : " + productId + " thành công " + images);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}

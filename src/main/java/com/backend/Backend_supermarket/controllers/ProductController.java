package com.backend.Backend_supermarket.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.repositorys.ProductImageRepository;
import com.backend.Backend_supermarket.responses.ProductResponse;
import com.backend.Backend_supermarket.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;
    private final ProductImageRepository productImageRepository;

    @PostMapping()
    public ResponseEntity<?> createProduct(
            @RequestBody @Valid ProductDTO productDTO,
            BindingResult result) {
        try {
            // lấy ra danh sách lỗi check được ở validation
            if (result.hasErrors()) {
                // lấy ra danh sách lỗi
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                // trả về danh sách lỗi
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ProductResponse response = productService.createProduct(productDTO);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        try {
            List<ProductResponse> responses = productService.getAllProduct();
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
        @PathVariable("id") Long productId
    ){
        try {
            ProductResponse responses = productService.getProductById(productId);
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
        @PathVariable("id") Long productId,
        @RequestBody @Valid ProductDTO productDTO
    ){
        try {
            ProductResponse responses = productService.updateProduct(productId, productDTO);
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
        @PathVariable("id") Long productId
    ){
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

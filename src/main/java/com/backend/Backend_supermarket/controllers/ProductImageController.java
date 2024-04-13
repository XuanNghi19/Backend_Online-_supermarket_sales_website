package com.backend.Backend_supermarket.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.services.ProductImageService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/productImage")
public class ProductImageController {
    private final ProductImageService productImageService;

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProductImage(
            @PathVariable("id") Long productId,
            @ModelAttribute("files") List<MultipartFile> files) {
        try {
            List<ProductImage> images = new ArrayList<>();
            // kiểm tra file có null hay không
            files = files == null ? new ArrayList<>() : files;
            if (files.size() > ProductImage.MAXIMUM_IMAGES
                    - productImageService.getSizeProductImageWithProductId(productId)) {
                return ResponseEntity.badRequest().body("Một sản phẩm tối đa chỉ nhận 3 ảnh");
            }
            for (MultipartFile file : files) {
                if (file.isEmpty())
                    continue;
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Dung lượng file ảnh quá lớn");
                }
                // kiểm tra xem có phải file ảnh không
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("Định dạng file phải ảnh file ảnh");
                }
                // lưu ảnh vào server
                String filename = storeImage(file);
                // lưu tên ảnh vào database
                ProductImage productImage = productImageService.createProductImageWithProductId(productId, filename);
                images.add(productImage);
            }
            return ResponseEntity.ok().body(images);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String imageUrl) {
        try {
            ProductImage productImage = productImageService.getProductImageByImageUrl(imageUrl);
            Path path = Paths.get("uploads/"+productImage.getImageUrl());
            UrlResource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> putMethodName(
            @PathVariable("id") Long productId,
            @ModelAttribute("files") List<MultipartFile> files) {
        try {
            List<ProductImage> images = new ArrayList<>();
            // kiểm tra file có null hay không
            files = files == null ? new ArrayList<>() : files;
            if (files.size() > ProductImage.MAXIMUM_IMAGES
                    - productImageService.getSizeProductImageWithProductId(productId)) {
                return ResponseEntity.badRequest().body("Một sản phẩm tối đa chỉ nhận 3 ảnh");
            }
            for (MultipartFile file : files) {
                if (file.isEmpty())
                    continue;
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Dung lượng file ảnh quá lớn");
                }
                // kiểm tra xem có phải file ảnh không
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("Định dạng file phải ảnh file ảnh");
                }
                // lưu ảnh vào server
                String filename = storeImage(file);
                // lưu tên ảnh vào database
                ProductImage productImage = productImageService.updateProductImageByImageUrl(productId, filename);
                images.add(productImage);
            }
            return ResponseEntity.ok().body(images);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/{imageName}")
    public ResponseEntity<?> deleteProductImage(
            @PathVariable("imageName") String imageName) {
        try {
            productImageService.deleteProductImage(imageName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeImage(MultipartFile file) throws IOException {
        // lấy ra đuôi của file
        String contentType = file.getContentType().substring(6);
        // Đổi tên file thành tên duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "." + contentType;
        // lấy ra đường dẫn thư mục uploads
        Path uploadDir = Paths.get("uploads");
        // kiểm tra thư mục đã tồn tại chưa
        if (!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }
        // đường dẫn tới file ảnh
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        // lưu file ảnh vào server
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}

package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.responses.ProductListResponse;
import com.backend.Backend_supermarket.responses.ProductResponse;
import com.backend.Backend_supermarket.responses.ResponseData;
import com.backend.Backend_supermarket.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseData<?> createProduct(
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
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
            }
            ProductResponse response = productService.createProduct(productDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo sản phẩm thành công", response);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping
    public ResponseData<?> getAllProducts(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "category_id", defaultValue = "0") Long categoryId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order // Thêm tham số 'order' để xác định hướng
                                                                             // sắp xếp
    ) {
        try {
            Direction direction = Sort.Direction.ASC;
            if (order.equalsIgnoreCase("desc")) {
                direction = Sort.Direction.DESC;
            }
            Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(direction, sort));
            Page<ProductResponse> pageResponse = productService.getAllProducts(keyword, categoryId, pageable);
            List<ProductResponse> products = pageResponse.toList();
            int totalPages = pageResponse.getTotalPages();
            return new ResponseData<>(
                    HttpStatus.CREATED.value(),
                    "Lấy danh sách sản phẩm thành công",
                    ProductListResponse.builder()
                            .products(products)
                            .totalPages(totalPages)
                            .currentPage(page)
                            .pageSize(limit)
                            .build());
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getProductById(
            @PathVariable("id") Long productId) {
        try {
            ProductResponse responses = productService.getProductById(productId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Lấy sản phẩm thành công", responses);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateProduct(
            @PathVariable("id") Long productId,
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
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), errorMessages.toString());
            }
            ProductResponse responses = productService.updateProduct(productId, productDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Cập nhật sản phẩm thành công", responses);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteProduct(
            @PathVariable("id") Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseData<>(HttpStatus.OK.value(), "Xóa sản phẩm thành công");
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
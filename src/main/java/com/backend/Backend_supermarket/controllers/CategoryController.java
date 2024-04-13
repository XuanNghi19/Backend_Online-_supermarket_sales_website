package com.backend.Backend_supermarket.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Backend_supermarket.services.CategoryService;
import com.backend.Backend_supermarket.models.Category;
import com.backend.Backend_supermarket.responses.ResponseData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseData<?> getAll(){
        try {
            List<Category> categories = categoryService.getAllCategory();
            return new ResponseData<>(HttpStatus.OK.value(), "Danh sách category", categories);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}

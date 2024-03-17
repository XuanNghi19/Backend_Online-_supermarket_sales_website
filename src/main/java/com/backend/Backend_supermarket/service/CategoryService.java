package com.backend.Backend_supermarket.service;

import java.util.List;

import com.backend.Backend_supermarket.dto.CategoryDTO;
import com.backend.Backend_supermarket.models.Category;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Category createCategory(CategoryDTO categoryDTO) throws Exception;
}

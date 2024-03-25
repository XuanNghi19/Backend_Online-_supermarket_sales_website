package com.backend.Backend_supermarket.services;

import java.util.List;

import com.backend.Backend_supermarket.dtos.CategoryDTO;
import com.backend.Backend_supermarket.models.Category;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Category createCategory(CategoryDTO categoryDTO) throws Exception;
}

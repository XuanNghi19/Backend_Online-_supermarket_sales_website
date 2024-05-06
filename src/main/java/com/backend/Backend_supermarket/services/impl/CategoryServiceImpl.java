package com.backend.Backend_supermarket.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.Backend_supermarket.dtos.CategoryDTO;
import com.backend.Backend_supermarket.models.Category;
import com.backend.Backend_supermarket.repositorys.CategoryRepository;
import com.backend.Backend_supermarket.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Category createCategory(CategoryDTO categoryDTO) throws Exception {
        if(categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
            throw new Exception("Loại hàng đã tồn tại");
        }
        Category category = Category.fromCategoryDTO(categoryDTO);
        return categoryRepository.save(category);
    }
    
}

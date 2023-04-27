package com.codelaninja.blog.category;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(Long categoryId);

    @Cacheable(value = "categoryCache")
    String getCategoryNameById(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategoryById(Long categoryId);
    
}

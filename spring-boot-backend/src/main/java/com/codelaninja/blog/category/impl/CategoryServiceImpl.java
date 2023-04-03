package com.codelaninja.blog.category.impl;

import com.codelaninja.blog.category.Category;
import com.codelaninja.blog.category.CategoryDto;
import com.codelaninja.blog.category.CategoryRepository;
import com.codelaninja.blog.category.CategoryService;
import com.codelaninja.blog.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category newCategory = categoryRepository.save(mapToEntity(categoryDto));

        return mapToDTO(newCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categories = categoryRepository.findAll();

        return categories
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return mapToDTO(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);

        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);

    }

    // convert DTO to entity
    private Category mapToEntity(CategoryDto categoryDto) {

        return mapper.map(categoryDto, Category.class);
    }

    private CategoryDto mapToDTO(Category category) {

        return mapper.map(category, CategoryDto.class);
    }
}

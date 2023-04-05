package com.codelaninja.blog.category;

import com.codelaninja.blog.category.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody CategoryDto categoryDto
    ) {

        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.OK);
    }

    @GetMapping
    public List<CategoryDto> getAllCategory() {

        return categoryService.getAllCategory();
    }

    @PostMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable Long id
    ) {

        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

}

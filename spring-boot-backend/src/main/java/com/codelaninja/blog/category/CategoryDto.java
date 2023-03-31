package com.codelaninja.blog.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    @NotEmpty
    @Size(
            min = 1,
            message = "Category name should have at least 1 characters"
    )
    private String name;
    private String description;

}


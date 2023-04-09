package com.codelaninja.blog.tag;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto implements Serializable {


    private Long id;
    @NotEmpty
    @Size(
            min = 1,
            message = "Tag name should have at least 1 characters"
    )
    private String name;


}

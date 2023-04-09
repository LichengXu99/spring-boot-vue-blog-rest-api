package com.codelaninja.blog.post;

import com.codelaninja.blog.tag.TagDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    // Post should not be null  or empty
    // Post should have at least 2 characters
    @NotEmpty
    @Size(
            min = 2,
            message = "Post title should have at least 2 characters"
    )
    private String title;

    // Post description should be not null or empty
    // Post description should have at least 10 characters
    @Size(
            min = 10,
            message = "Post description should have at least 10 characters"
    )
    private String description;

    // Post content should not be null or empty
    @NotEmpty
    private String content;

    private Date createDate;

    private Date lastUpdateDate;

    private Status status;

    private Long userId;

    private Long categoryId;

    private List<TagDto> tags;

}

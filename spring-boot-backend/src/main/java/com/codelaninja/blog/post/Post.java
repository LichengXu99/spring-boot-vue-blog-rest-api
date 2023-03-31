package com.codelaninja.blog.post;

import com.codelaninja.blog.category.Category;
import com.codelaninja.blog.tag.Tag;
import com.codelaninja.blog.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "POSTS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"TITLE"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            length = 30,
            nullable = false
    )
    private String title;
    private String description;
    @Column(
            columnDefinition = "TEXT",
            nullable = false
    )
    private String content;
    @Column(
            columnDefinition = "DATETIME",
            nullable = false
    )
    private Date releaseDate;
    @Column(
            columnDefinition = "DATETIME",
            nullable = false
    )
    private Date lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

}

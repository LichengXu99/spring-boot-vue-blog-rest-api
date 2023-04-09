package com.codelaninja.blog.post;

import com.codelaninja.blog.category.Category;
import com.codelaninja.blog.tag.Tag;
import com.codelaninja.blog.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;

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
    @CreatedDate
    @Column(
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
            nullable = false
    )
    private Date createDate;
    @LastModifiedDate
    @Column(
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
            nullable = false
    )
    private Date lastUpdateDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "POST_TAG",
            joinColumns = {@JoinColumn(name = "TAG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "POST_ID")}
    )
    private List<Tag> tags;

}

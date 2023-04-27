package com.codelaninja.blog.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Specification<Post> spec, Pageable pageable);
    List<Post> findByCategoryId(Long categoryId);
    @Query("SELECT p FROM Post p WHERE EXISTS (SELECT 1 FROM p.tags t WHERE t.id = :tagId)")
    List<Post> findByTagId(@Param("tagId") Long tagId);
}


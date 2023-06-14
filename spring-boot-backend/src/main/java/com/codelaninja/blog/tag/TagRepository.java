package com.codelaninja.blog.tag;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.name LIKE '%' || :keyword || '%'")
    List<Tag> findByTagName(@Param("keyword") String keyword);
}
package com.codelaninja.blog.post;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir, Long tagId, Long categoryId, String keyword);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByTag(Long tagId);


}

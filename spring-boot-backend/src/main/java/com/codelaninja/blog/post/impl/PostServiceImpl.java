package com.codelaninja.blog.post.impl;

import com.codelaninja.blog.category.Category;
import com.codelaninja.blog.category.CategoryRepository;
import com.codelaninja.blog.exception.ResourceNotFoundException;
import com.codelaninja.blog.post.*;
import com.codelaninja.blog.tag.Tag;
import com.codelaninja.blog.tag.TagRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        List<Tag> tags = new ArrayList<>();
        if (postDto.getTags() != null) {
            tags = postDto.getTags()
                    .stream()
                    .map(tagDto -> tagRepository.findById(tagDto.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagDto.getId())))
                    .collect(Collectors.toList());
        }

        // Convert DTO to entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        post.setTags(tags);
        post.setCreateDate(Date.valueOf(LocalDate.now()));
        post.setLastUpdateDate(Date.valueOf(LocalDate.now()));
        Post newPost = postRepository.save(post);

        // Convert entity to DTO
        return mapToDTO(newPost);
    }


    @Override
    @Cacheable(value = "postCache")
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir, Long tagId, Long categoryId, String keyword) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Specification<Post> spec = Specification.where(null);

        if (tagId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("tags").get("id"), tagId));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId));
        }
        if (StringUtils.hasText(keyword)) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + keyword + "%"));
        }

        Page<Post> posts = postRepository.findAll(spec, pageable);

        // Get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts
                .stream()
                .map(this::mapToDTO)
                // .map(post -> mapToDTO(post))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDTO(post);
    }

    @Override
    @Transactional
    public PostDto updatePost(PostDto postDto, Long id) {
        // Get post by id from database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        List<Tag> tags = new ArrayList<>();
        if (postDto.getTags() != null) {
            tags = postDto.getTags()
                    .stream()
                    .map(tagDto -> tagRepository.findById(tagDto.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagDto.getId())))
                    .collect(Collectors.toList());
        }

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setLastUpdateDate(Date.valueOf(LocalDate.now()));
        post.setStatus(postDto.getStatus());
        post.setUser(post.getUser());
        post.setCategory(category);
        post.setTags(tags);
        Post updatedPost = postRepository.save(post);

        return mapToDTO(updatedPost);
    }

    @Override
    @Transactional
    public void deletePostById(Long id) {
        // Get post by id from the database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }

    @Override
    @Cacheable(value = "postCache")
    public List<PostDto> getPostsByCategory(Long categoryId) {

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByTag(Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));

        List<Post> posts = postRepository.findByTagId(tagId);

        return posts
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapToDTO(Post post) {
        PostDto postDto = mapper.map(post, PostDto.class);
        Long categoryId = post.getCategory().getId();
        String categoryName = post.getCategory().getName();
        postDto.setCategoryId(categoryId);
        postDto.setCategoryName(categoryName);
        return postDto;
    }
}


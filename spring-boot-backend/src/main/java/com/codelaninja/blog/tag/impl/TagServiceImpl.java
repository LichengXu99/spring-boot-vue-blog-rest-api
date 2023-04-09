package com.codelaninja.blog.tag.impl;

import com.codelaninja.blog.exception.ResourceNotFoundException;
import com.codelaninja.blog.tag.Tag;
import com.codelaninja.blog.tag.TagDto;
import com.codelaninja.blog.tag.TagRepository;
import com.codelaninja.blog.tag.TagService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    @Override
    public TagDto addTag(TagDto tagDto) {

        Tag newTag = tagRepository.save(mapToEntity(tagDto));

        return mapToDTO(newTag);
    }

    @Override
    public List<TagDto> getAllTag() {

        List<Tag> tags = tagRepository.findAll();

        return tags
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "tagCache")
    public TagDto getTagById(Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));

        return mapToDTO(tag);
    }

    @Override
    public TagDto updateTag(TagDto tagDto, Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
        tag.setName(tagDto.getName());
        Tag updatedTag = tagRepository.save(tag);

        return mapToDTO(updatedTag);
    }

    @Override
    public void deleteTagById(Long tagId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
        tagRepository.delete(tag);

    }

    // convert DTO to entity
    private Tag mapToEntity(TagDto tagDto) {

        return mapper.map(tagDto, Tag.class);
    }

    private TagDto mapToDTO(Tag tag) {

        return mapper.map(tag, TagDto.class);
    }
}


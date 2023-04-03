package com.codelaninja.blog.tag;

import java.util.List;

public interface TagService {

    TagDto addTag(TagDto tagDto);

    List<TagDto> getAllTag();

    TagDto getTagById(Long categoryId);

    TagDto updateTag(TagDto tagDto, Long tagId);

    void deleteTagById(Long tagId);
    
}

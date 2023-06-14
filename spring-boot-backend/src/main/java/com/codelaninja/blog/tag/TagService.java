package com.codelaninja.blog.tag;

import java.util.List;

public interface TagService {

    TagDto addTag(TagDto tagDto);

    List<TagDto> getAllTag();

    TagDto getTagById(Long categoryId);

    List<TagDto> getTagByKeyword(String keyword);

    TagDto updateTag(TagDto tagDto, Long tagId);

    void deleteTagById(Long tagId);
    
}

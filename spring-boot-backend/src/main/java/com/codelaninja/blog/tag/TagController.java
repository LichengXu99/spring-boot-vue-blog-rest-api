package com.codelaninja.blog.tag;

import com.codelaninja.blog.tag.impl.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagServiceImpl tagService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TagDto> addTag(
            @RequestBody TagDto tagDto
    ) {

        return new ResponseEntity<>(tagService.addTag(tagDto), HttpStatus.OK);
    }

    @GetMapping
    public List<TagDto> getAllTag() {

        return tagService.getAllTag();
    }

    @PostMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(
            @PathVariable Long id
    ) {

        return new ResponseEntity<>(tagService.getTagById(id), HttpStatus.OK);
    }

}

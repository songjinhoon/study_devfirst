package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.devfirst.admin.epic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post/api")
public class PostApiController {

    private final PostService postService;

    @PostMapping("/save")
    public Long save(PostRequestDto postRequestDto, MultipartFile multipartFile) {
        return postService.save(postRequestDto);
    }

    @PostMapping("/saveForJson")
    public Long save(@RequestBody PostRequestDto postRequestDto) {
        return postService.save(postRequestDto);
    };

    @GetMapping("/find/{id}")
    public PostResponseDto find(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping("/update/{id}")
    public PostResponseDto find(@RequestBody PostRequestDto postRequestDto, @PathVariable Long id) {
        return postService.update(postRequestDto, id);
    }
}

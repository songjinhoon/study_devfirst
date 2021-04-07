package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.QPostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import com.devfirst.admin.epic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/th")
public class ThymeleafController {

    private final PostService postService;

    @GetMapping(value = "/index")
    public String getIndex(Model model, SearchDto searchDto) throws Exception {
        PostRequestDto postRequestDto = PostRequestDto.builder()
               .title("타이틀")
               .content("내용")
               .userId(1L)
               .build();
        for(int i=0; i<100; i++) {
            postService.save(postRequestDto);
        }
        PageRequest pageRequest = PageRequest.of(searchDto.getPage() - 1, 10, Sort.Direction.ASC, "id");
        Page<QPostResponseDto> result = postService.findAll(pageRequest, searchDto);
        model.addAttribute("posts", result.getContent());
        return "index";
    }
}

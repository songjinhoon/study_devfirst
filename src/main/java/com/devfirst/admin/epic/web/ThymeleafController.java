package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.config.auth.LoginUser;
import com.devfirst.admin.epic.config.auth.dto.SessionUser;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/th")
public class ThymeleafController {

    private final PostService postService;

    @GetMapping(value = "/main")
    public String getMain(SearchDto searchDto, Model model) throws Exception {
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
        return "thymeleaf/defaultLayout";
    }

    @GetMapping(value = "/index")
    public String getIndex(@LoginUser SessionUser sessionUser, SearchDto searchDto, Model model) throws Exception {
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
        model.addAttribute("date", new Date());
        model.addAttribute("pageNum", 5);
        model.addAttribute("sessionUser", sessionUser); // 잠만? 이거 필요함? 차피 있는디?
        return "thymeleaf/index";
    }

    @GetMapping(value = "/hrefTest")
    public String getHrefTest(@RequestParam(name = "pageNum") int pageNum, Model model) throws Exception {
        model.addAttribute("response", "@RequestParam");
        model.addAttribute("pageNum", pageNum);
        return "thymeleaf/index2";
    }

    @GetMapping(value = "/hrefTest/{pageNum}")
    public String getHrefTestWithpageNum(@PathVariable(name = "pageNum") int pageNum, Model model) {
        model.addAttribute("response", "@PathVariable");
        model.addAttribute("pageNum", pageNum);
        return "thymeleaf/index2";
    }
}

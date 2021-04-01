package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.common.PaginationInfo;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devfirst.admin.epic.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;

	@GetMapping("/list")
	public String index(SearchDto searchDto, Model model) {
		PageRequest pageRequest = PageRequest.of(searchDto.getPage() - 1, 10, Sort.Direction.ASC, "id");
		Page<PostResponseDto> result = postService.findAll(pageRequest, searchDto);
		PaginationInfo paginationInfo = PaginationInfo.builder()
				.totalPageCnt(result.getTotalPages())
				.pageable(result.getPageable())
				.build();
		model.addAttribute("posts", result.getContent());
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("parameter", searchDto);
		return "/post/index";
	}

	@GetMapping("/write")
	public String postWrite() {
		return "/post/postWrite";
	}

	/*	@GetMapping("/list")
	public String getIndex(SearchDto searchDto, Model model) {
		System.out.println("?");
		PageRequest pageRequest = PageRequest.of(searchDto.getPage() - 1, 4, Sort.Direction.ASC, "id");
		model.addAttribute("map", postService.findAll(pageRequest, searchDto));
		model.addAttribute("parameter", searchDto);
		return "/post/index";
	}*/
}
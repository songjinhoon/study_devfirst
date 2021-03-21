package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.dto.SearchDto;
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
	public String getIndex() {
		System.out.println("::1@@@::");
		return "/post/index";
	}
	
	@PostMapping("/list")
	public String postIndex(Model model, SearchDto searchDto) {
		System.out.println("::@2@::");
		PageRequest pageRequest = PageRequest.of(searchDto.getPage(), 4, Sort.Direction.ASC, "id");
		model.addAttribute("map", postService.findAll(pageRequest));
		return "/post/postList";
	}
}
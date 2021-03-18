package com.devfirst.admin.epic.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.devfirst.admin.epic.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/list")
	public String index(Model model) {
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
		model.addAttribute("map", postService.findAll(pageRequest));
		
		return "/post/index";
	}
}
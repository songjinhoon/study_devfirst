package com.devfirst.admin.epic.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devfirst.admin.epic.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/post")
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/list")
	public String index(Model model, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		//PageRequest pageRequest = PageRequest.of(0, 4, Sort.Direction.ASC, "id");
		//model.addAttribute("map", postService.findAll(pageRequest));
		model.addAttribute("map", postService.findAll(pageable));
		
		return "/post/index";
	}
}
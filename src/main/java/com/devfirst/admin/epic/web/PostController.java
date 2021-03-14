package com.devfirst.admin.epic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@GetMapping("/list")
	public String index() {
		System.out.println("오잉33");
		return "/post/index";
	}
}
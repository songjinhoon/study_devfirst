package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.config.auth.LoginUser;
import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import com.devfirst.admin.epic.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping("/index")
	public String getIndex(@LoginUser SessionUser sessionUser, Model model) {
		model.addAttribute("sessionUser", sessionUser);
		return "/dashboard/index";
	}
}

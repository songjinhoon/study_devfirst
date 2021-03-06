package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.config.auth.LoginUser;
import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(@LoginUser SessionUser sessionUser, Model model) {
        if(sessionUser != null) {
            model.addAttribute("sessionUser", sessionUser);
        }
        return "/index";
    }
}

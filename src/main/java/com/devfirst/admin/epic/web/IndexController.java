package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.config.auth.LoginUser;
import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devfirst.admin.epic.dto.LombokDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(@LoginUser SessionUser sessionUser, Model model) {
        if(sessionUser != null) {
            System.out.println("::DEBUG::");
            model.addAttribute("sessionUser", sessionUser);
        }
        return "/index";
    }
}

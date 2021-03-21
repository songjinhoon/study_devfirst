package com.devfirst.admin.epic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devfirst.admin.epic.dto.LombokDto;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "/index";
    }
}

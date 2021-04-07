package com.devfirst.admin.epic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/th")
public class ThymeleafController {

    @GetMapping(value = "/index")
    public String getIndex() {
        return "index";
    }
}

package com.devfirst.admin.epic.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @GetMapping("/index")
    public String getIndex() {
        return "/statistics/index";
    }
}

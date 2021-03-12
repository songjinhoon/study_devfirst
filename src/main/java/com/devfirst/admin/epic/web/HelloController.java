package com.devfirst.admin.epic.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devfirst.admin.epic.dto.LombokDto;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public LombokDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new LombokDto(name, amount);
    }
}

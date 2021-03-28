package com.devfirst.admin.epic.config;

import com.devfirst.admin.epic.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
/*
* 1. 스프링에서 읽을 수 있게 설정해주는 클래스
* 2. HandlerMehotdArgumentResolver는 항상 WebMvcConfigure.addArgumentResolvers()를 통해 추가해야 한다.
* */
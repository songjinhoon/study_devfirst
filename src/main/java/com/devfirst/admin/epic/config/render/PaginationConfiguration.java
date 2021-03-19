package com.devfirst.admin.epic.config.render;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaginationConfiguration {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public PaginationRenderer paginationRenderer() {
        return new PaginationRendererImpl(contextPath);
    }
}

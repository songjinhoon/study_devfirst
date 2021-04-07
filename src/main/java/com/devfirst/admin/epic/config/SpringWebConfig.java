package com.devfirst.admin.epic.config;

import com.devfirst.admin.epic.common.DateFormatter;
import com.devfirst.admin.epic.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.List;

@RequiredArgsConstructor
//@EnableWebMvc
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Value("${spring.thymeleaf.cache}")
    private boolean isCache;

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override // 핸들러 메소드 추가
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

    /* 타임리프 설정 */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Messages");
        return messageSource;
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        /*registry.addFormatter(varietyFormatter());*/
        registry.addFormatter(dateFormatter());
    }

    /*@Bean
    public VarietyFormatter varietyFormatter() {
        return new VarietyFormatter();
    }*/

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver ();
        //templateResolver.setPrefix("classpath:templates/thymeleaf/");
        templateResolver.setPrefix("file:src/main/resources/templates/thymeleaf/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(isCache);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(MessageSource messageSource) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource);
        templateEngine.addDialect(layoutDialect());

        return templateEngine;
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public ThymeleafViewResolver viewResolver(MessageSource messageSource) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine(messageSource));
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(0);
        return viewResolver;
    }
}
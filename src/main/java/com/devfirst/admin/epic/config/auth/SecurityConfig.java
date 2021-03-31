package com.devfirst.admin.epic.config.auth;

import com.devfirst.admin.epic.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()  //h2-console 사용하기 위함 
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/out/**").permitAll()
                    //.antMatchers("/**/api/**").hasRole(Role.USER.name())
                    //.anyRequest().authenticated()
                    .antMatchers("/**/api/**").permitAll()
                    .anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth2 로그인 성공 후 뒤 메소드들 실행하게 됨
                            .userService(customOAuth2UserService);

    }
}

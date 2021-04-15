package com.devfirst.admin.epic.config.auth;

import com.devfirst.admin.epic.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /* 시큐리티 사용 시 필터는 여기다 걸어줘야한다. */
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .csrf().disable()
                .headers().frameOptions().disable()  //h2-console 사용하기 위함 
                .and()
                    .authorizeRequests()
                    .antMatchers("/css/**", "/images/**", "/js/**", "/h2-console/**", "/out/**").permitAll()
                    .anyRequest().authenticated()
                    //.antMatchers("/**/api/**").hasRole(Role.USER.name())
                    //.antMatchers("/**/api/**").permitAll()
                    //.anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth2 로그인 성공 후 뒤 메소드들 실행하게 됨
                            .userService(customOAuth2UserService);

    }
}

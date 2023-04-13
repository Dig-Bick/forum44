package com.example.forum4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
                .and()
            .csrf().disable() // 禁用 CSRF 防护
            .authorizeRequests()
               .antMatchers("/api/register", "/api/login", "/admin/register", "/admin/login", "/admin/user-management", "/user/list", "/user/delete/{id}", "/api/posts", "/api/categories", "/api/postManagement/posts", "/api/postManagement/posts/**","/api/categories/all","/api/posts/recommended/**","/api/posts/recommended").permitAll()

                .anyRequest().authenticated()
                .and()
            .httpBasic() // 使用基本的 HTTP 身份验证
                .and()
            .logout()
                .permitAll();
    }

    // ...
}

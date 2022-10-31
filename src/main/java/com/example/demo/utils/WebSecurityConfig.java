package com.example.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
       .httpBasic().and()
       .authorizeRequests()
       .antMatchers(HttpMethod.POST, "/user/add").permitAll()
       .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
       .anyRequest().authenticated()
       .and().csrf().disable();


        return http.build();
    }

}
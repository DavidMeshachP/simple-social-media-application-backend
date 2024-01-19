package com.zerplabsintern.simplesocialmediawebapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain applicationSecurity (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers("/login","/register").permitAll()
            .anyRequest().authenticated()
        );

        return httpSecurity.build();

    }
    
}

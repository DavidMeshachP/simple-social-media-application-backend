package com.zerplabsintern.simplesocialmediawebapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.zerplabsintern.simplesocialmediawebapplication.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean 
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // UserDetails user = User.withUsername("Mikee")
        // .password(passwordEncoder.encode("alnassr"))
        // .build();
        
        // return new InMemoryUserDetailsManager(user);

        return new UserServiceImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf((csrf) ->csrf.disable())
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated()
        );

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

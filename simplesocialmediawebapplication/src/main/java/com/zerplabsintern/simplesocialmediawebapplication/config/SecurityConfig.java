package com.zerplabsintern.simplesocialmediawebapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
            .csrf((csrf) ->csrf.disable())
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers("/login","/register/users").permitAll()
            .anyRequest().authenticated()
        );

    }

    // @Bean 
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    //     // UserDetails user = User.withUsername("Mikee")
    //     // .password(passwordEncoder.encode("alnassr"))
    //     // .build();
        
    //     // return new InMemoryUserDetailsManager(user);

    //     return userDetailsService;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

    //     httpSecurity
    //         .csrf((csrf) ->csrf.disable())
    //         .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
    //         .requestMatchers("/login","/register/users").permitAll()
    //         .anyRequest().authenticated()
    //     );

    //     return httpSecurity.build();

    // }

    
}

package com.zerplabsintern.simplesocialmediawebapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    // @Qualifier("UserServiceImpl")
    private UserService userService;

     @Autowired 
     private UserDetailsService userDetailsService;

    @Bean 
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // UserDetails user = User.withUsername("Mikee")
        // .password(passwordEncoder.encode("alnassr"))
        // .build();
        
        // return new InMemoryUserDetailsManager(user);

        return userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf((csrf) ->csrf.disable())
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers("/login","/register/users").permitAll()
            .anyRequest().authenticated()
        );



        return httpSecurity.build();

    }

    // @Bean
	// public PasswordEncoder passwordEncoder() {
	// 	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// }

    // @Bean 
    // public AuthenticationManager authenticationManager ( UserDetailsService userDetailsService, PasswordEncoder passwordEncoder ) {

    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    //     authenticationProvider.setUserDetailsService(userDetailsService);

    //     authenticationProvider.setPasswordEncoder(passwordEncoder);

    //     ProviderManager providerManager = new ProviderManager(authenticationProvider);

    //     providerManager.setEraseCredentialsAfterAuthentication(false);

    //     return providerManager;

    // }


    
}

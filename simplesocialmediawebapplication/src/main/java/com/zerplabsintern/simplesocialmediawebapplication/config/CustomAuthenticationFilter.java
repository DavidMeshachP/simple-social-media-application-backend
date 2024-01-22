// package com.zerplabsintern.simplesocialmediawebapplication.config;

// import java.io.IOException;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
// import com.zerplabsintern.simplesocialmediawebapplication.service.CustomAuthenticationFilterService;
// import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Configuration
// public class CustomAuthenticationFilter extends OncePerRequestFilter{

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private UserRepository userRepository;

//     // @Autowired
//     // private CustomAuthenticationFilterService customAuthenticationFilterService;

//     @Bean
//     public AuthenticationManager authenticationManager(  ) {
        
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
        
//         extractTokenFromRequest(request);

//     }

//     private Optional<String> extractTokenFromRequest(HttpServletRequest request) {

//         String authHeader = request.getHeader("Authorization");

//         if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
//             return Optional.of(authHeader.substring(7));
//         }

//         return Optional.empty();

//     }
    
// }

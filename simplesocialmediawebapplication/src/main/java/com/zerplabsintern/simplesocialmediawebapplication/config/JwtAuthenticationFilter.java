package com.zerplabsintern.simplesocialmediawebapplication.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.websphere.WebSpherePreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;
import com.zerplabsintern.simplesocialmediawebapplication.service.impl.CustomUserDetailsServiceImpl;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtTokenProvider.extractUserName(jwt);

        if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null ) {
            UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(userEmail);

            if(jwtTokenProvider.isTokenValid(userEmail, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );

                token.setDetails(new WebSpherePreAuthenticatedWebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);

            }

        }
        filterChain.doFilter(request, response);
        
    }

    // private JwtTokenProvider jwtTokenProvider;

    // private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    // @Autowired
    // public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
    //     this.jwtTokenProvider = jwtTokenProvider;
    // }

    // @Autowired
    // public void setCustomUserDetailsServiceImpl(CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
    //     this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
    // }

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    //     try {

    //         String jwt = extractJwtFromRequest(request);

    //         if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {

    //             String userEmail = jwtTokenProvider.getUserEmailFromToken(jwt);

    //             UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(userEmail);

    //             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
    //                     userDetails, null, null);
    //             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    //             SecurityContextHolder.getContext().setAuthentication(authentication);

    //         }

    //     } catch (Exception e) {

    //         logger.error("Could not set user authentication in security context", e);

    //     }

    //     filterChain.doFilter(request, response);

    // }

    // private String extractJwtFromRequest(HttpServletRequest request) {

    //     String bearerToken = request.getHeader("Authorization");

    //     if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
    //         return bearerToken.substring(7);
    //     }

    //     return null;

    // }

}

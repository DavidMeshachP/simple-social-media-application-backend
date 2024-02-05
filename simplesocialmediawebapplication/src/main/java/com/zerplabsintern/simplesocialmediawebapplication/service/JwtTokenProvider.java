package com.zerplabsintern.simplesocialmediawebapplication.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenProvider {

    // String createToken(String email);

    // boolean validateToken(String Token);

    // String getUserEmailFromToken(String token);

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String createToken( String emailId );
    
}

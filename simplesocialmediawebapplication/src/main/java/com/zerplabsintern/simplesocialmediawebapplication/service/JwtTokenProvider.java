package com.zerplabsintern.simplesocialmediawebapplication.service;

public interface JwtTokenProvider {

    String extractUserName(String token);

    // String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token);

    String createToken( String emailId );
    
    
}

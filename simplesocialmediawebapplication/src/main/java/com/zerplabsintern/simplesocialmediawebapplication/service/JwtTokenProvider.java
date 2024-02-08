package com.zerplabsintern.simplesocialmediawebapplication.service;

public interface JwtTokenProvider {

    String extractUserName(String token);

    boolean isTokenExpired(String token);

    String createToken( String emailId );
    
    
}

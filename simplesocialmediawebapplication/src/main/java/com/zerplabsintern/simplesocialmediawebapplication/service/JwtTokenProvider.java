package com.zerplabsintern.simplesocialmediawebapplication.service;

public interface JwtTokenProvider {

    String createToken(String email);

    boolean validateToken(String Token);

    String getUserEmailFromToken(String token);
    
}

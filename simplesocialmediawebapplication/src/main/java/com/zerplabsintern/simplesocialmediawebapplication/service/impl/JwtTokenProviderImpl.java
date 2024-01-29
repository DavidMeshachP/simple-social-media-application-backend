package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private final Key secretKey =Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long validityInMilliseconds = 3 * 3600000;

    public String createToken( String userEmail) {

        Date currentDate = new Date();
        Date validity = new Date(currentDate.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt( currentDate )
                .setExpiration( validity )
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String getUserEmailFromToken(String token) {

        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();

    }

    @Override
    public boolean validateToken(String token) {
        
        try {

            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;

            
        } catch ( JwtException | IllegalArgumentException e ) {
            
            return false;

        }

    }

    
}

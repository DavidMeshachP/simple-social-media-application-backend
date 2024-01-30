package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.exception.JwtTokenProviderException;
import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long validityInMilliseconds = 3 * 3600000;

    public String createToken( String userEmail) {

        if(userEmail.isEmpty()){
            throw new JwtTokenProviderException("the user email is empty, it cannot be empty...");
        }
        else {

            Date currentDate = new Date();
            Date validity = new Date(currentDate.getTime() + validityInMilliseconds);

            return Jwts.builder()
                    .setSubject(userEmail)
                    .setIssuedAt( currentDate )
                    .setExpiration( validity )
                    .signWith(secretKey)
                    .compact();

        }
    }

    @Override
    public String getUserEmailFromToken(String token) {

        if(token.isEmpty()) {
            throw new JwtTokenProviderException("the token is empty, it cannot be empty...");
        }
        else {
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims.getSubject();

        }

    }

    @Override
    public boolean validateToken(String token) {

        try {

            if( token.isEmpty()) {
                throw new JwtTokenProviderException("the given token is empty, it cannot be empty...");
            }
            else {
                Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                return true;
            }


        } catch (JwtException | IllegalArgumentException e) {

            throw new JwtTokenProviderException("exception occured while trying to validate token....");

        }

    }

}

package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.exception.JwtTokenProviderException;
import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private String secretKey = "yffqg1slcR3L/2qqdmwl02iN4Be3W2lQGQ6FtMKyNNFh81GW2+QrmxkJafuYRfw+";

    public String createToken( String emailId ) {

        return Jwts.builder()
            .setSubject(emailId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(getSignKey(),SignatureAlgorithm.HS256)
            .compact();

    }

    public String extractUserName ( String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSignKey() {

        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);

    }

    private Claims extractAllClaims(String token) {
        if( token == null ) {
            throw new JwtTokenProviderException("token is null, check the data and send again. ");
        }
        else {

            try {

                Claims jwt = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
                return jwt;
            } catch (Exception e) {
                throw new JwtTokenProviderException("token parsing error, check again " + e);
            }
        }
    }

    public boolean isTokenValid(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}

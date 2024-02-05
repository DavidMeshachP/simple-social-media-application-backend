package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    // private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private String secretKey = "yffqg1slcR3L/2qqdmwl02iN4Be3W2lQGQ6FtMKyNNFh81GW2+QrmxkJafuYRfw+";

    // private final long validityInMilliseconds = 3 * 3600000;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignKey(),SignatureAlgorithm.HS256)
            .compact();
        
    }

    public String createToken( String emailId ) {

        return Jwts.builder()
            .setSubject(emailId)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
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
        return Jwts.parserBuilder().
            setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails ) {
        final String username = extractUserName(token);
        return ( username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return extractClaims(token, Claims::getExpiration).before(new Date());

    }

    // public String createToken( String userEmail) {

    //     if(userEmail.isEmpty()){
    //         throw new JwtTokenProviderException("the user email is empty, it cannot be empty...");
    //     }
    //     else {

    //         Date currentDate = new Date();
    //         Date validity = new Date(currentDate.getTime() + validityInMilliseconds);

    //         return Jwts.builder()
    //                 .setSubject(userEmail)
    //                 .setIssuedAt( currentDate )
    //                 .setExpiration( validity )
    //                 .signWith(secretKey)
    //                 .compact();

    //     }
    // }

    // @Override
    // public String getUserEmailFromToken(String token) {

    //     if(token.isEmpty()) {
    //         throw new JwtTokenProviderException("the token is empty, it cannot be empty...");
    //     }
    //     else {
    //         Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    //         return claims.getSubject();

    //     }

    // }

    // @Override
    // public boolean validateToken(String token) {

    //     try {

    //         if( token.isEmpty()) {
    //             throw new JwtTokenProviderException("the given token is empty, it cannot be empty...");
    //         }
    //         else {
    //             Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    //             return true;
    //         }


    //     } catch (JwtException | IllegalArgumentException e) {

    //         throw new JwtTokenProviderException("exception occured while trying to validate token....");

    //     }

    // }

}

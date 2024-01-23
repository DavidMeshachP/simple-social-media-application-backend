package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired UserRepository userRepository;

    @Override
    public String checkAndGetEmailId(LoginDto loginDto) {

        List<User> users = userService.getAllUser(loginDto.getUserName());

        if (!users.isEmpty()) {
            
            for (User user : users) {

                if(user.getPassword().equals(loginDto.getPassword())){
                    return user.getEmailId();
                }

            }

        }
        else {
            return null;
        }

        return null;

    }

    @Override
    public String checkLoginAndGenerateToken(LoginDto loginDto) {

        List<User> users = userService.getAllUser(loginDto.getUserName());

        if (!users.isEmpty()) {
            
            for (User user : users) {

                if(user.getPassword().equals(loginDto.getPassword())){
                    return generateToken(user.getEmailId());
                }

            }

        }
        else {
            return null;
        }

        return null;
    }

    public String generateToken(String userEmail ) {

        Map<String,Object> a = new HashMap<>();
        return createToken(a,userEmail);

    }

    private String createToken(Map<String,Object> a, String userEmail) {
        return Jwts.builder()
                 .setClaims(a) 
                 .setSubject(userEmail)
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                 .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {

        byte[] kbytes = Decoders.BASE64.decode("Ny5uPhGK8v0zi6eD7QsTNIblmxOXz3D+ceNa/jpRW7zSQ8ZQDtnaguR1wqrl2b/p");

        return Keys.hmacShaKeyFor(kbytes);
    }

}

package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.service.CustomAuthenticationFilterService;

@Service
public class CustomAuthenticationFilterServiceImpl implements CustomAuthenticationFilterService {

    @Override
    public String extractEmail(String token) {
        
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedString = new String(decodedBytes);

        return decodedString;
    }

    @Override
    public String generateToken(UserDto userDto) {

        String base64EncodedString = Base64.getEncoder().encodeToString(userDto.getEmailId().getBytes());

        return base64EncodedString;
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        
        String email = extractEmail(token);

        if(email.equals(user.getEmailId())){
            return true;
        }
        else{
            return false;
        }
    }

    
    
}

package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public String checkLoginAndGenerateToken(LoginDto loginDto) {

        List<User> users = userService.getAllUser(loginDto.getUserName());

        if (!users.isEmpty()) {
            
            for (User user : users) {

                if(user.getPassword().equals(loginDto.getPassword())){
                    return encodeBase64(user.getEmailId());
                }

            }

        }
        else {
            return null;
        }

        return null;
    }

    private String encodeBase64(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
        return new String(encodedBytes);
    }

}

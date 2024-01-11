package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

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
    public boolean checkLoginAndGenerateToken(LoginDto loginDto) {

        List<User> users = userService.getAllUser(loginDto.getUserName());

        if (!users.isEmpty()) {
            
            for (User user : users) {

                if(user.getPassword().equals(loginDto.getPassword())){
                    return true;
                }

            }

        }
        else {
            return false;
        }

        return false;
    }

}

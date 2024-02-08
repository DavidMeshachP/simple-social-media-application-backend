package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.exception.LoginServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String checkLoginAndGenerateToken(LoginDto loginDto) {

        User user = userRepository.getReferenceById(userRepository.findIdbyemailId(loginDto.getEmailIdString()));

        if( user != null && user.getPassword().equals(loginDto.getPassword()) ) {
            return jwtTokenProvider.createToken(user.getEmailId());
        }
        else {
            throw new LoginServiceException("no user found, please check the details...");
        }
    }

}

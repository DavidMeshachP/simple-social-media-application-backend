package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProviderImpl jwtTokenProviderImpl;

    @Override
    public String checkAndGetEmailId(LoginDto loginDto) {

        User user = userRepository.getReferenceById(userRepository.findIdbyemailId(loginDto.getEmailIdString()));

        if ( user != null ) {
            return user.getEmailId();
        }
        else {
            return null;
        }

    }

    @Override
    public String checkLoginAndGenerateToken(LoginDto loginDto) {

        User user = userRepository.getReferenceById(userRepository.findIdbyemailId(loginDto.getEmailIdString()));

        if( user != null ) {
            return jwtTokenProviderImpl.createToken(user.getEmailId());
        }
        else {
            return null;
        }
    }

}

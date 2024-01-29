package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;

public interface LoginService {

    String checkLoginAndGenerateToken(LoginDto loginDto);

    String checkAndGetEmailId(LoginDto loginDto);
    
}

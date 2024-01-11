package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;

public interface LoginService {

    boolean checkLoginAndGenerateToken(LoginDto loginDto);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody LoginDto loginDto) {

        String encodeString = loginService.checkLoginAndGenerateToken(loginDto);

        if( !encodeString.isEmpty() ) {
            return new ResponseEntity<>(encodeString,HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>("User credentials not authenticated",HttpStatus.UNAUTHORIZED);
        }
        
    }
    
}

package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.exception.LoginServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody LoginDto loginDto) {

        try {

            String jwtString = loginService.checkLoginAndGenerateToken(loginDto);

            if (jwtString == null) {
                throw new LoginServiceException("give correct credentials..");
            } else {
                return new ResponseEntity<>(jwtString, HttpStatus.OK);
            }

        } catch (Exception e) {
            throw new LoginServiceException("exception occured while checking and generating token.. ");
        }

    }

}

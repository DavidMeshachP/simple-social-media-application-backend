package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LoginDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.LoginService;
import com.zerplabsintern.simplesocialmediawebapplication.service.JwtTokenProvider;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody LoginDto loginDto) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmailIdString(), loginDto.getPassword()));

            if (authentication.isAuthenticated()) {

                String jwtString = jwtTokenProvider.createToken(loginService.checkAndGetEmailId(loginDto));

                return new ResponseEntity<>(jwtString, HttpStatus.OK);

            }

            else {

                return new ResponseEntity<>("error not authorized give correct credentials", HttpStatus.UNAUTHORIZED);

            }

        }

        catch (Exception e) {

            System.err.println(e);

        }

        return new ResponseEntity<>("error, not authorized, give correct credentials", HttpStatus.UNAUTHORIZED);

    }

}

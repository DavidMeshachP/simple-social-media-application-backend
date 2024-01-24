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

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody LoginDto loginDto) {


        try{

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmailIdString(), loginDto.getPassword()));

        

        String jwtString = loginService.generateToken(loginService.checkAndGetEmailId(loginDto));

        if(authentication.isAuthenticated()) {

            return new ResponseEntity<>(jwtString, HttpStatus.OK);

        }

        else {

            return new ResponseEntity<>("error not authorized give correct credentials", HttpStatus.UNAUTHORIZED);

        }

    }

    catch (Exception e) {

        System.err.println(e);

    }


        // if( jwtString != null ) {
        //     return new ResponseEntity<>(jwtString,HttpStatus.OK) ;
        // }
        // else {
        //     return new ResponseEntity<>("User credentials not authenticated",HttpStatus.UNAUTHORIZED);
        // }

        return new ResponseEntity<>("error not authorized give correct credentials", HttpStatus.UNAUTHORIZED);

        
    }

    
    
}

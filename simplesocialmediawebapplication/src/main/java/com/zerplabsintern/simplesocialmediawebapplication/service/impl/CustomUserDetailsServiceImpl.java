package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.exception.CustomUserDetailsServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userEmailString) throws UsernameNotFoundException {
        
        User user = userService.findUserByEmailId(userEmailString);

        if( user == null ) {
            throw new CustomUserDetailsServiceException("no user found by the given details....");
        }

        else {

            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmailId())
                    .password(user.getPassword())
                    .authorities(Collections.emptyList())
                    .build();
    
            return userDetails;

        }

    } 
    
}

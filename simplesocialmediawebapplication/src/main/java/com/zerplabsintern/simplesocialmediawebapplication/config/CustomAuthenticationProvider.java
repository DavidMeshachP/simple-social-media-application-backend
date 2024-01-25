package com.zerplabsintern.simplesocialmediawebapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if( authentication.getCredentials() == null ) {

            throw new BadCredentialsException("Credentials cannot be null");

        }

        String presentPassword = authentication.getCredentials().toString();

        if ( !passwordsMatch(presentPassword, userDetails.getPassword()) ) {

            throw new BadCredentialsException("passwords do not match");

        }
        
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        
        User user = userService.findUserByEmailId(username);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailId())
                .password(user.getPassword())
                .build();

        if( userDetails == null) {

            throw new UsernameNotFoundException("User not found with username : " + username);

        }

        return userDetails;
    }

    private boolean passwordsMatch( String presentPassword, String userDetailsPassword ) {

        if( presentPassword.equals(userDetailsPassword)) {
            return true;
        }
        else {
            return false;
        }

    }
    
    
}

package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @PostMapping("/register/users")
    public ResponseEntity<?> createUser( @RequestBody UserDto userDto ) {
        try {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The Saving of the User did not Succeed." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser( @PathVariable Long id, @RequestBody UserDto userDto, Authentication authentication) {
        
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(userService.updateUser(id,userDto, currentUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The Updating of the User did not Succeed." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The retrieving of the User did not Succeed." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-users/{name}")
    public ResponseEntity<?> getAllUser(@PathVariable String name) {
        try {
            return new ResponseEntity<>(userService.getAllUser(name),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The retrieving of the User did not Succeed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {

        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(userService.deleteUser(id,currentUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The deleting of the User did not Succeed." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
}

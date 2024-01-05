package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser( @RequestBody User user ) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The Saving of the User did not Succeed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/registration/{id}")
    public ResponseEntity<?> updateUser( @PathVariable Long id, @RequestBody User user) {
        
        return new ResponseEntity<>(userService.updateUser(id,user), HttpStatus.OK);
    }

    
    
}

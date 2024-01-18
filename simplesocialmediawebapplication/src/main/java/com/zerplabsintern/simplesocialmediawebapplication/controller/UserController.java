package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser( @RequestBody UserDto userDto ) {
        try {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("The Saving of the User did not Succeed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser( @PathVariable Long id, @RequestBody UserDto userDto) {
        
        return new ResponseEntity<>(userService.updateUser(id,userDto), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }

    @GetMapping("/all-users/{name}")
    public ResponseEntity<?> getAllUser(@PathVariable String name) {
        return new ResponseEntity<>(userService.getAllUser(name),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
    
    
}

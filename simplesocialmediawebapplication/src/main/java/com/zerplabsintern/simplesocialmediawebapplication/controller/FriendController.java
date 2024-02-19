package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.FriendDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.FriendService;
import com.zerplabsintern.simplesocialmediawebapplication.service.impl.FriendServiceImpl;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService = new FriendServiceImpl();

    @GetMapping("/friends/{id}")
    public ResponseEntity<?> getFriends(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(friendService.getFriends(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(@RequestBody FriendDto friendDto, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(friendService.addFriend(friendDto, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/friends")
    public ResponseEntity<?> removeFriend(@RequestBody FriendDto friendDto, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(friendService.deleteFriends(friendDto.getUserId(),friendDto.getFriendId(), currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("checkdata",HttpStatus.BAD_REQUEST);
        }
    }
    
}

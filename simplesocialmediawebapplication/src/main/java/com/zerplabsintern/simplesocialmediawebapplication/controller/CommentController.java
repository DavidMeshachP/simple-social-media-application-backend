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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(commentService.addComment(commentDto, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data" + e ,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comments")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(commentService.updateComment(commentDto, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again" + e,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, Authentication authentication) {
        try {
            
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(commentService.removeComment(id, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again" + e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getAllComments(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentService.getAllComment(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again" + e,HttpStatus.BAD_REQUEST);
        }
    }

    
}

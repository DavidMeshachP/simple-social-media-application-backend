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

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostService;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto, Authentication authentication) {

        try {

            UserDetails currentUser = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(postService.save(postDto, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data that was sent again.",HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto postDto, Authentication authentication) {

        try {

            UserDetails currentUser = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(postService.updatePost(id, postDto, currentUser),HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>("check the data that was sent again.",HttpStatus.BAD_REQUEST);
        }

    }
    
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Authentication authentication){

        try {

            UserDetails currentUser = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(postService.deletePost(id, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data that was sent again.",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data that was sent again.",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user-posts/{userId}")
    public ResponseEntity<?> getAllPostByUserId(@PathVariable Long userId) {

        try {
            return new ResponseEntity<>(postService.getAllPostByUserId(userId),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }
    
}

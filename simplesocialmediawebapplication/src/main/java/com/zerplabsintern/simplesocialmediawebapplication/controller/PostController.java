package com.zerplabsintern.simplesocialmediawebapplication.controller;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {

        try {
            return new ResponseEntity<>(postService.save(post),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check the data that was sent again.",HttpStatus.BAD_REQUEST);
        }

    }
    
}

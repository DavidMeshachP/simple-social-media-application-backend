package com.zerplabsintern.simplesocialmediawebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostImagesService;

@RestController
public class PostImagesController {

    @Autowired
    private PostImagesService postImagesService;

    @PostMapping("/post-images")
    public ResponseEntity<?> createPostImages(@RequestBody PostImagesDto postImagesDto) {
        try {
            return new ResponseEntity<>(postImagesService.addPostImages(postImagesDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save the images..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post-images/{id}")
    public ResponseEntity<?> updatePostImages(@RequestParam Long id,@RequestBody PostImagesDto postImagesDto) {
        try {
            return new ResponseEntity<>(postImagesService.updatePostImage(id, postImagesDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save the images..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post-images/{id}")
    public ResponseEntity<?> deletePostImages(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(postImagesService.deletePostImages(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save the images..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

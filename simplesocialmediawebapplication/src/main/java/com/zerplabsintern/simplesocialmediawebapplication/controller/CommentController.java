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

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;
import com.zerplabsintern.simplesocialmediawebapplication.likeDto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto) {
        try {
            return new ResponseEntity<>(commentService.addComment(commentDto),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data",HttpStatus.OK);
        }
    }

    @PutMapping("/comment")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(commentService.updateComment(comment),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again",HttpStatus.OK);
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentService.removeComment(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again",HttpStatus.OK);
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getAllComments(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(commentService.getAllComment(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check data again",HttpStatus.OK);
        }
    }

    
}

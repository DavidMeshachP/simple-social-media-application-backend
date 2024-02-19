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

import com.zerplabsintern.simplesocialmediawebapplication.dto.LikeDto;
import com.zerplabsintern.simplesocialmediawebapplication.repository.LikeRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeRepository likeRepository;
    
    @GetMapping("/likes/{id}")
    public ResponseEntity<?> getLikesOfPost(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(likeService.getLike(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("check check checkkk" + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/likes/{id}")
    public boolean removeLike(@PathVariable Long id, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            if(likeService.removeLike(likeRepository.getReferenceById(id), currentUser)){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/likes")
    public ResponseEntity<?> addLike(@RequestBody LikeDto likeDto, Authentication authentication) {
        try {
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(likeService.addLike(likeDto, currentUser),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("some error ",HttpStatus.BAD_REQUEST);
        }
    }

}

package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto, UserDetails currentUser);

    CommentDto updateComment(CommentDto commentDto, UserDetails currentUser);

    boolean removeComment(Long id, UserDetails currentUser);

    List<CommentDto> getAllComment(Long id);
    
    boolean deleteByPostId(Long id);
}

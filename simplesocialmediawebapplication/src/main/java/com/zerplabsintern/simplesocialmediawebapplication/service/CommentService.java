package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto);

    CommentDto updateComment(CommentDto commentDto);

    boolean removeComment(Long id);

    List<CommentDto> getAllComment(Long id);    
}

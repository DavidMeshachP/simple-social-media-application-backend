package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto);

    CommentDto updateComment(Comment comment);

    boolean removeComment(Long id);

    List<CommentDto> getAllComment(Long id);    
}

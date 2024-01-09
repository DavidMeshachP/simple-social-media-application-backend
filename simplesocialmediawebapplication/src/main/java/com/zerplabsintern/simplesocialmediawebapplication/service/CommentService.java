package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;

public interface CommentService {

    Comment addComment(CommentDto commentDto);

    Comment updateComment(Comment comment);

    boolean removeComment(Long id);

    List<Comment> getAllComment(Long id);    
}

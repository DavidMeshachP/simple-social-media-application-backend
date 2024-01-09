package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;
import com.zerplabsintern.simplesocialmediawebapplication.repository.CommentRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeComment(Long id) {
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Comment updateComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Comment> getAllComment(Long id) {
        try {
            return commentRepository.findCommentByPostId(id);
        } catch (Exception e) {
            return null;
        }
    }

    
    
}

package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.likeDto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.repository.CommentRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment addComment(CommentDto commentDto) {
        try {
            Comment newComment = new Comment();
            newComment.setId(commentDto.getId());
            newComment.setComments(commentDto.getComment());
            User user = userRepository.findById(commentDto.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Post post = postRepository.findById(commentDto.getId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));
            newComment.setcUser(user);
            newComment.setcPost(post);
            newComment.setComments(commentDto.getComment());
            return commentRepository.save(newComment);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeComment(Long id) {
        try {
            if(commentRepository.findById(id).isPresent()) {

                commentRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
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
            return commentRepository.findBycPost_Id(id);
        } catch (Exception e) {
            return null;
        }
    }

    
    
}

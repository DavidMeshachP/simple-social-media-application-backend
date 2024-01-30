package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.exception.CommentServiceException;
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
    public CommentDto addComment(CommentDto commentDto) {
        try {
            Comment newComment = new Comment();
            newComment.setComment(commentDto.getComment());
            User user = userRepository.findById(commentDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            Post post = postRepository.findById(commentDto.getPostId())
                    .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            newComment.setcUser(user);
            newComment.setcPost(post);
            commentRepository.save(newComment);
            commentDto.setId(newComment.getId());
            return commentDto;
        } catch (Exception e) {
            throw new CommentServiceException(
                    "exception occured while trying to save the comment, check the details that was sent again....");
        }
    }

    @Override
    public boolean removeComment(Long id) {
        try {
            if (commentRepository.findById(id).isPresent()) {

                commentRepository.deleteById(id);
                return true;
            } else {
                throw new CommentServiceException("there is no comment by that id, check your details again...");
            }
        } catch (Exception e) {
            throw new CommentServiceException(
                    "exception occured when trying to remove comment, check the details that was sent again....");
        }
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        try {
            Comment newComment = new Comment();

            newComment.setId(commentDto.getId());
            newComment.setComment(commentDto.getComment());
            newComment.setcPost(postRepository.getReferenceById(commentDto.getPostId()));
            newComment.setcUser(userRepository.getReferenceById(commentDto.getUserId()));
            newComment.setCreated(commentRepository.getReferenceById(commentDto.getId()).getCreated());

            commentRepository.save(newComment);

            return commentDto;

        } catch (Exception e) {
            throw new CommentServiceException(
                    "exception occured while trying to update comment, check the details that was sent again....");
        }
    }

    @Override
    public List<CommentDto> getAllComment(Long id) {
        try {
            List<Comment> comment = commentRepository.findBycPost_Id(id);

            if (comment.size() == 0) {
                throw new CommentServiceException(
                        "the comment could not be found, please check the details that was sent again... ");
            }

            else {

                List<CommentDto> commentDtos = new ArrayList<>();

                for (Comment c : comment) {

                    CommentDto commentDto = new CommentDto();

                    commentDto.setId(c.getId());
                    commentDto.setPostId(c.getcPost().getId());
                    commentDto.setUserId(c.getcUser().getId());
                    commentDto.setComment(c.getComment());

                    commentDtos.add(commentDto);

                }

                return commentDtos;
            }

        } catch (Exception e) {
            throw new CommentServiceException("exception occured while trying to get all the comments by using the post id, check the details that was sent again...");
        }
    }

}

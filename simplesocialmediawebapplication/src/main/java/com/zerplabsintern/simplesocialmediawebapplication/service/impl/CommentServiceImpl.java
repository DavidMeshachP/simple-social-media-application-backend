package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.CommentDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;
import com.zerplabsintern.simplesocialmediawebapplication.exception.CommentServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.CommentRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto addComment(CommentDto commentDto, UserDetails currentUser) {
        try {
            Comment newComment = new Comment();

            if(commentDto.getComment() != null ) {

                newComment.setComment(commentDto.getComment());
            }
            else {
                throw new CommentServiceException("comment cannot be null, check the data that was sent again");
            }

            if(commentDto.getUserId() != null ) {

                Long userId = userRepository.findIdbyemailId(currentUser.getUsername());

                if(userId == commentDto.getUserId()) {

                    newComment.setcUser(userRepository.findById(commentDto.getUserId()).get());
                }
                else {
                    throw new CommentServiceException("wrong user id, the logged in user does not match with the given user");
                }

                
            }
            else {
                throw new CommentServiceException("userId cannot be null, check the user id that was sent again");
            }

            if( commentDto.getPostId() != null ) {

                newComment.setcPost(postRepository.findById(commentDto.getPostId()).get());
            }
            else {
                throw new CommentServiceException("post id cannot be cannot be found ");
            }

            commentRepository.save(newComment);

            commentDto.setId(newComment.getId());

            return commentDto;
        } catch (Exception e) {
            throw new CommentServiceException("exception occured while trying to save the comment, check the details that was sent again....");
        }
    }

    @Override
    public boolean removeComment(Long id, UserDetails currentUser) {
        try {
            if (commentRepository.findById(id).isPresent()) {

                if(userRepository.findIdbyemailId(currentUser.getUsername()) == commentRepository.findById(id).get().getcUser().getId() ) {

                    commentRepository.deleteById(id);

                }

                return true;
            } else {
                throw new CommentServiceException("there is no comment by that id, check your details again...");
            }
        } catch (Exception e) {
            throw new CommentServiceException("exception occured when trying to remove comment, check the details that was sent again....");
        }
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, UserDetails currentUser) {
        try {
            Comment newComment = new Comment();

            if(commentDto.getId() != null) {

                if(userRepository.findIdbyemailId(currentUser.getUsername()) == commentRepository.findById(commentDto.getId()).get().getcUser().getId() ) {

                    newComment.setId(commentDto.getId());
                }
                else {
                    throw new CommentServiceException("the user id does not match with the provided user id.");
                }

            }
            else {
                throw new CommentServiceException("id field cannot be null");
            }

            if(commentDto.getComment() != null ) {

                newComment.setComment(commentDto.getComment());
            }

            if( commentDto.getPostId() != null ) {
                newComment.setcPost(postRepository.findById(commentDto.getPostId()).get());
            }

            if ( commentDto.getUserId() != null ) {
                newComment.setcUser(userRepository.findById(commentDto.getUserId()).get());
            }
            
            newComment.setCreated(commentRepository.getReferenceById(commentDto.getId()).getCreated());

            commentRepository.save(newComment);

            return commentDto;

        } catch (Exception e) {
            throw new CommentServiceException("exception occured while trying to update comment, check the details that was sent again....");
        }
    }

    @Override
    public List<CommentDto> getAllComment(Long id) {
        try {
            List<Comment> comment = commentRepository.findBycPost_Id(id);

            if (comment.size() == 0) {
                throw new CommentServiceException("the comment could not be found, please check the details that was sent again... ");
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

    @Override
    public boolean deleteByPostId(Long id) {

        if( commentRepository.findBycPost_Id(id).isEmpty() ) {

            return false;
        }
        else {
            commentRepository.deleteByPost_Id(id);
            return true;
        }

    }    

}

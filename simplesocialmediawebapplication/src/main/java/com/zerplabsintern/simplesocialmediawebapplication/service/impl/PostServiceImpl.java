package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.exception.PostServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.CommentService;
import com.zerplabsintern.simplesocialmediawebapplication.service.LikeService;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostImagesService;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostService;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostImagesService postImagesService;

    public PostDto save(PostDto postDto, UserDetails currentUser) {

        Post newPost = new Post();
        
        try {

            newPost.setCaption( postDto.getCaption());

            if(postDto.getMode() != null ) {

                newPost.setMode(postDto.getMode());
            }
            else {
                throw new PostServiceException("mode field cannot be null, check the data that was sent again");
            }

            if(postDto.getUserId() != null ) {

                if( userRepository.findIdbyemailId(currentUser.getUsername()) == postDto.getUserId()) {

                    newPost.setpUser(userRepository.getReferenceById(postDto.getUserId()));
                }
                else {
                    throw new PostServiceException("user id associated with this request is not the same as logged in user");
                }
                
            }
            else{
                throw new PostServiceException("userId cannot be null, check the data that was sent again..");
            }

            postRepository.save(newPost);

        } 
        catch (Exception e) {
            throw new PostServiceException("Error while saving post, check the data that was sent again..");
        }

        postDto.setId(newPost.getId());

        return postDto;
        
    }


    public PostDto updatePost(Long id, PostDto postDto, UserDetails currentUser ) {

        if(postRepository.findById(id).isPresent()){

            Post newPost = new Post();

            if(postDto.getCaption() != null ) {

                newPost.setCaption( postDto.getCaption());
            }

            if( postDto.getUserId() != null ) {

                if( userRepository.findIdbyemailId(currentUser.getUsername()) == postDto.getUserId()) {
                    User user = userRepository.findById(postDto.getUserId()).get();
    
                    if(user != null ) {
    
                        newPost.setpUser(user);
                    }
                    else {
                        throw new PostServiceException("no such user, check the data again ");
                    }
                }
                else {
                    throw new PostServiceException("the logged in user is not matching with the requested user post.");
                }

            }
            else {
                throw new PostServiceException("user id cannot be null, check the data that was sent again..");
            }

            if(postDto.getId() != null ) {

                newPost.setId(postDto.getId());
            }
            else {
                throw new PostServiceException("id cannot be null, check the data that was sent again..");
            }

            if(postDto.getMode() != null ) {

                newPost.setMode(postDto.getMode());
            }
            
            newPost.setCreated(postRepository.findById(postDto.getId()).get().getCreated());

            postRepository.save(newPost);

            return postDto;

        }
        else {
            throw new PostServiceException("no post by this id is found for updating..");
        }

    }


    @Override
    @Transactional
    public boolean deletePost(Long id, UserDetails currentUser ) {
        
        try {

            if( userRepository.findIdbyemailId(currentUser.getUsername()) == postRepository.findById(id).get().getpUser().getId() ) {

                if(postRepository.findById(id).isPresent()) {
    
                    commentService.deleteByPostId(id);
    
                    likeService.deleteByPostId(id);
    
                    postImagesService.deleteByPostId(id);
    
                    postRepository.deleteById(id);
    
                    return true;
                }
                else{
                    throw new PostServiceException("no post found by this id to delete..");
                }
            }
            else {
                throw new PostServiceException("the requested user does not match with the logged in user.");
            }

        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
    }


    @Override
    public Post getPostById(Long id) {

        try {
            if(postRepository.findById(id).isPresent()){
                return postRepository.getReferenceById(id);
            }
            else {
                throw new PostServiceException("Post could not be found for returning ..");
            }
        } catch (Exception e) {
            throw new PostServiceException(e.getMessage());
        }
    }


    @Override
    public List<Post> getAllPostByUserId(Long id) {

        if(userRepository.findById(id).isPresent()){
            return postRepository.findBypUser_Id(id);

        }
        else {
            throw new PostServiceException("Post could not be found for returning..");
        }
    }
    
}

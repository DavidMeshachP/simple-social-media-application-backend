package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.exception.PostServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostDto save(PostDto postDto) {

        Post newPost = new Post();
        
        try {

            newPost.setCaption( postDto.getCaption());
            newPost.setMode(postDto.getMode());
            newPost.setpUser(userRepository.getReferenceById(postDto.getUserId()));

            postRepository.save(newPost);

        } 
        catch (Exception e) {
            throw new PostServiceException("Error while saving post, check the data that was sent again..");
        }

        postDto.setId(newPost.getId());

        return postDto;
        
    }


    public PostDto updatePost(Long id, PostDto postDto) {

        if(postRepository.findById(id).isPresent()){

            Post newPost = new Post();

            newPost.setCaption( postDto.getCaption());
            newPost.setId(postDto.getId());
            newPost.setMode(postDto.getMode());
            newPost.setpUser(userRepository.getReferenceById(postDto.getUserId()));
            newPost.setCreated(postRepository.getReferenceById(postDto.getId()).getCreated());

            postRepository.save(newPost);

            return postDto;

        }
        else {
            throw new PostServiceException("no post by this id is found for updating..");
        }

    }


    @Override
    public boolean deletePost(Long id) {
        
        try {
            if(postRepository.findById(id).isPresent()) {
                postRepository.deleteById(id);
                return true;
            }
            else{
                throw new PostServiceException("no post found by this id to delete..");
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

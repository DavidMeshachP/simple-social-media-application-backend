package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post save(PostDto postDto) {

        try {

            Post newPost = new Post();

            newPost.setCaption( postDto.getCaption());
            newPost.setId(postDto.getId());
            newPost.setMode(postDto.getMode());
            newPost.setpUser(userRepository.getReferenceById(postDto.getUserId()));

            postRepository.save(newPost);

        } 
        catch (Exception e) {
            return null;
        }

        return postRepository.getReferenceById(postDto.getId());
        
    }


    public Post updatePost(Long id, PostDto postDto) {

        Post newPost = new Post();

        newPost.setCaption( postDto.getCaption());
        newPost.setId(postDto.getId());
        newPost.setMode(postDto.getMode());
        newPost.setpUser(userRepository.getReferenceById(postDto.getUserId()));
        newPost.setCreated(postRepository.getReferenceById(postDto.getId()).getCreated());

        postRepository.save(newPost);

        return newPost;

    }


    @Override
    public Post deletePost(Long id) {
        
        try {
            if(postRepository.findById(id).isPresent()) {
                postRepository.deleteById(id);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }


    @Override
    public Post getPostById(Long id) {

        try {
            if(postRepository.findById(id).isPresent()){
                return postRepository.getReferenceById(id);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }


    @Override
    public List<Post> getAllPostByUserId(Long id) {

        if(userRepository.findById(id).isPresent()){
            return postRepository.findBypUser_Id(id);

        }
        else {
            return null;
        }
    }
    
}

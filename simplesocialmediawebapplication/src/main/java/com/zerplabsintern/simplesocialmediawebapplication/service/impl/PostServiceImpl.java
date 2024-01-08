package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Post save(Post post) {

        try {
            Optional<Post> postOptional = postRepository.findById(post.getId());
            if(postOptional.isPresent()){

                updatePost(post.getId(), post);

            }
            else {

                postRepository.save(post);

            }

        } 
        catch (Exception e) {
            return null;
        }

        return postRepository.getReferenceById(post.getId());
        
    }


    public Post updatePost(Long id, Post post) {

        Post newPost = new Post();

        newPost.setId(id);
        newPost.setImage(post.getImage());
        newPost.setCaption(post.getCaption());
        newPost.setMode(post.getMode());
        newPost.setCreated(post.getCreated());

        postRepository.save(newPost);

        return null;

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
            return postRepository.findByUserId(id);

        }
        else {
            return null;
        }
    }
    
}

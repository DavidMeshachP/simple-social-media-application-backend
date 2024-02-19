package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface PostService {

    PostDto save(PostDto postDto, UserDetails currentUser);

    PostDto updatePost(Long id, PostDto postDto, UserDetails currentUser);

    boolean deletePost(Long id, UserDetails currentUser);

    Post getPostById(Long id);

    List<Post> getAllPostByUserId(Long id);
    
}

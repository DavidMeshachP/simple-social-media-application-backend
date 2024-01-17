package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface PostService {

    Post save(PostDto postDto);

    Post updatePost(Long id, PostDto postDto);

    Post deletePost(Long id);

    Post getPostById(Long id);

    List<Post> getAllPostByUserId(Long id);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface PostService {

    Post save(Post post);

    Post updatePost(Long id, Post post);

    Post deletePost(Long id);

    Post getPostById(Long id);

    List<Post> getAllPostByUserId(Long id);
    
}

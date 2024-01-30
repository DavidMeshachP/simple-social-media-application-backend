package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface PostService {

    PostDto save(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    boolean deletePost(Long id);

    Post getPostById(Long id);

    List<Post> getAllPostByUserId(Long id);
    
}

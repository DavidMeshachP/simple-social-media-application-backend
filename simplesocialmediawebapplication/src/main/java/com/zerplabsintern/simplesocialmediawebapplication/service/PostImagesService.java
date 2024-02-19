package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;

public interface PostImagesService {

    PostImagesDto addPostImages(PostImagesDto postImagesDto, UserDetails currentUser);

    boolean deletePostImages(Long id, UserDetails currentUser);

    List<PostImages> getPostImages(Long id);

    boolean deleteByPostId(Long id);
    
}

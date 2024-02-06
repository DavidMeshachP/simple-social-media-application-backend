package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;

public interface PostImagesService {

    PostImagesDto addPostImages(PostImagesDto postImagesDto);

    boolean deletePostImages(Long id);

    List<PostImages> getPostImages(Long id);

    boolean deleteByPostId(Long id);
    
}

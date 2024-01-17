package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;

public interface PostImagesService {

    PostImagesDto addPostImages(PostImagesDto postImagesDto);

    boolean deletePostImages(Long id);

    PostImagesDto updatePostImage(Long id, PostImagesDto postImagesDto);
    
}

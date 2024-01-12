package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;

public interface PostImagesService {

    PostImagesDto addPostImages(PostImagesDto postImagesDto);

    boolean deletePostImages(PostImagesDto postImagesDto);

    PostImagesDto updatePostImage(PostImagesDto postImagesDto);
    
}

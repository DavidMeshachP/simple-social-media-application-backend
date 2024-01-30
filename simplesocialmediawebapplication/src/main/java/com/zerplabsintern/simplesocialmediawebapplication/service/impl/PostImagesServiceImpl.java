package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;
import com.zerplabsintern.simplesocialmediawebapplication.exception.PostImagesServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostImagesRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostImagesService;

@Service
public class PostImagesServiceImpl implements PostImagesService {

    @Autowired
    private PostImagesRepository postImagesRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostImagesDto addPostImages(PostImagesDto postImagesDto) {
        try {

            PostImages postImages = new PostImages();

            postImages.setId(postImagesDto.getId());
            postImages.setImage(postImagesDto.getImage());
            postImages.setpIPost(postRepository.getReferenceById(postImagesDto.getId()));
            postImagesRepository.save(postImages);

            return postImagesDto;
            
        } catch (Exception e) {
            throw new PostImagesServiceException("exception while trying to save Post Images...");
        }
    }

    @Override
    public boolean deletePostImages( Long id ) {
        try {
            postImagesRepository.deleteById(postImagesRepository.getReferenceById(id).getId());;
            return true;
        } catch (Exception e) {
            throw new PostImagesServiceException("there is a exception while trying to delete the postImages... ");
        }
    }

    @Override
    public PostImagesDto updatePostImage(Long id, PostImagesDto postImagesDto) {

        try {
            PostImages postImages = new PostImages();

            postImages.setId(id);
            postImages.setImage(postImagesDto.getImage());
            postImagesRepository.save(postImages);

            return postImagesDto;


        } catch (Exception e) {
            throw new PostImagesServiceException("there is a error while updating the post Images...");
        }
    }

    
    
}

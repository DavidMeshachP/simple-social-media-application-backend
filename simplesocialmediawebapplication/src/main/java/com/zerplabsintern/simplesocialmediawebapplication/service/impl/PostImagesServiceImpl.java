package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;
import java.util.List;

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

            if(postImagesDto.getImage() != null ) {

                postImages.setImage(Base64.getDecoder().decode(postImagesDto.getImage()));
            }
            else {
                throw new PostImagesServiceException("image field cannot be null ");
            }

            if(postImagesDto.getPostId() != null ) {

                postImages.setpIPost(postRepository.findById(postImagesDto.getPostId()).get());
            }
            else {
                throw new PostImagesServiceException("postId field cannot be null");
            }

            postImagesRepository.save(postImages);

            return postImagesDto;
            
        } catch (Exception e) {
            throw new PostImagesServiceException("exception while trying to save Post Images...");
        }
    }

    @Override
    public boolean deletePostImages( Long id ) {
        try {
            if(postImagesRepository.findById(id).isPresent()){

                postImagesRepository.deleteById(id);;
                return true;
            }
            else {
                throw new PostImagesServiceException("the id given does not correspond to any of the post images, so please check the id and send the request again ");
            }
        } catch (Exception e) {
            throw new PostImagesServiceException("there is a exception while trying to delete the postImages... ");
        }
    } 

    @Override
    public List<PostImages> getPostImages(Long id) {

        if(postImagesRepository.findbyPostId(id) != null ) {

            List<PostImages> postImages = postImagesRepository.findbyPostId(id);
            return postImages;

        }
        else{
            throw new PostImagesServiceException("the given post_id doesn't have any images with it...");
        }

    }

}

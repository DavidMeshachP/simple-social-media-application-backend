package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.PostImagesDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;
import com.zerplabsintern.simplesocialmediawebapplication.exception.PostImagesServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.exception.UserServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostImagesRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.CompressImageService;
import com.zerplabsintern.simplesocialmediawebapplication.service.PostImagesService;

@Service
public class PostImagesServiceImpl implements PostImagesService {

    @Autowired
    private PostImagesRepository postImagesRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CompressImageService compressImageService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostImagesDto addPostImages(PostImagesDto postImagesDto, UserDetails currentUser) {
        try {

            PostImages postImages = new PostImages();

            if(postImagesDto.getImage() != null ) {

                if( compressImageService.checkLessThanFiveMB( postImagesDto.getImage()) ) {

                    postImages.setImage(Base64.getDecoder().decode(postImagesDto.getImage()));
                }
                else {
                    try {
                        postImages.setImage(Base64.getDecoder().decode(compressImageService.compressImage(postImagesDto.getImage(), 0, 0, 0.7f)));
                    } catch (Exception e) {
                        throw new UserServiceException("there is a error while compressing a image. "+ e);
                    }
                }
            }
            else {
                throw new PostImagesServiceException("image field cannot be null ");
            }

            if(postImagesDto.getPostId() != null ) {

                if (postRepository.findById(postImagesDto.getPostId()).get().getpUser().getId() == userRepository.findIdbyemailId(currentUser.getUsername()) ) {

                    postImages.setpIPost(postRepository.findById(postImagesDto.getPostId()).get());
                }
                else {
                    throw new PostImagesServiceException("the given post id user is not matching with the logged in user.");
                }

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
    public boolean deletePostImages( Long id, UserDetails currentUser ) {
        try {

            if(userRepository.findIdbyemailId(currentUser.getUsername()) == postRepository.findById(postImagesRepository.findById(id).get().getpIPost().getId()).get().getpUser().getId() ) {

                if(postImagesRepository.findById(id).isPresent()){
    
                    postImagesRepository.deleteById(id);;
                    return true;
                }
                else {
                    throw new PostImagesServiceException("the id given does not correspond to any of the post images, so please check the id and send the request again ");
                }
            }
            else {
                throw new PostImagesServiceException("the user corresponding to this request does not match with the logged in user");
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

    @Override
    public boolean deleteByPostId(Long PostId) {

        try {
            if(postImagesRepository.findbyPostId(PostId) != null){

                postImagesRepository.deleteByPost_Id(PostId);
                return true;
            }
            else {
                throw new PostImagesServiceException("the id given does not correspond to any of the post images, so please check the id and send the request again ");
            }
        } catch (Exception e) {
            throw new PostImagesServiceException("there is a exception while trying to delete the postImages... ");
        }
    }

}

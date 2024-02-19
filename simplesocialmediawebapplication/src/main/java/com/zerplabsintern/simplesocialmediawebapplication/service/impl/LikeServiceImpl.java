package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LikeDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.exception.LikeServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.LikeRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.LikeService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LikeDto addLike(LikeDto likeDto, UserDetails currentUser) {
        try {
            Likes newLike = new Likes();

            if( likeDto.getUserId() != 0L ) {

                if( userRepository.findIdbyemailId(currentUser.getUsername()) == likeDto.getUserId()) {

                    newLike.setlUser(userRepository.getReferenceById(likeDto.getUserId()));
                }
                else {
                    throw new LikeServiceException("the user id is different from the logged in user.");
                }

            }
            else {
                throw new LikeServiceException("user is not found, give correct values ");
            }

            if( likeDto.getPostId() != 0L ) {

                newLike.setlPost(postRepository.findById(likeDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found")));;
            }
            else {
                throw new LikeServiceException("the given post id is not found, give correct values");
            }

            likeRepository.save(newLike);

            likeDto.setId(newLike.getId());

            return likeDto;
            
        } catch (Exception e) {
            throw new LikeServiceException("there is a exception while trying to add a like...");
        }
    }

    @Override
    public List<LikeDto> getLike(Long postId) {
        try {
            if(!likeRepository.findLikesBylPost(postId).isEmpty()){
                List<LikeDto> likeDtos = new ArrayList<>();
                List<Likes> like = likeRepository.findLikesBylPost(postId);

                for(Likes likes : like ) {
                    LikeDto likeDto = new LikeDto();

                    likeDto.setId(likes.getId());
                    likeDto.setPostId(likes.getlPost().getId());
                    likeDto.setUserId(likes.getlUser().getId());

                    likeDtos.add(likeDto);
                }
                
                return likeDtos;
            }
            else{
                throw new LikeServiceException("there is no like by the given post id...");
            }
        } catch (Exception e) {
            throw new LikeServiceException(e.getMessage());
        }
    }

    @Override
    public boolean removeLike(Likes like, UserDetails currentUser) {
        try {
            if (userRepository.findIdbyemailId(currentUser.getUsername()) == like.getlUser().getId() ) {

                if(likeRepository.findById(like.getId()).isPresent()){
                    likeRepository.deleteById(like.getId());
                    return true;
                }
                else{
                    throw new LikeServiceException("there is no like by the given data, check the given data that was given again...");
                }
            }
            else {
                throw new LikeServiceException("the like given does not match with the users login. ");
            }
        } catch (Exception e) {
            throw new LikeServiceException("exception occured while trying to remove like ...");
        }
    }

    @Override
    public boolean deleteByPostId(Long id) {

        if( likeRepository.findLikesBylPost(id).isEmpty() ) {

            return false;
        }
        else {

            likeRepository.deleteByPost_Id(id);

            return true;
        }

    }
    
}

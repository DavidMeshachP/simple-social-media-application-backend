package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public LikeDto addLike(LikeDto likeDto) {
        try {
            Likes newLike = new Likes();

            if( likeDto.getUserId() != 0L ) {

                newLike.setlUser(userRepository.getReferenceById(likeDto.getUserId()));
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
    public List<LikeDto> getLike(Long id) {
        try {
            if(likeRepository.existsBylPostId(id)){
                List<LikeDto> likeDtos = new ArrayList<>();
                List<Likes> like = likeRepository.findLikesBylPost(postRepository.getReferenceById(id));
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
                throw new LikeServiceException("there is no like by the given id...");
            }
        } catch (Exception e) {
            throw new LikeServiceException(e.getMessage());
        }
    }

    @Override
    public boolean removeLike(Likes like) {
        try {
            if(likeRepository.findById(like.getId()).isPresent()){
                likeRepository.deleteById(like.getId());
                return true;
            }
            else{
                throw new LikeServiceException("there is no like by the given data, check the given data that was given again...");
            }
        } catch (Exception e) {
            throw new LikeServiceException("exception occured while trying to remove like ...");
        }
    }

    
    
}

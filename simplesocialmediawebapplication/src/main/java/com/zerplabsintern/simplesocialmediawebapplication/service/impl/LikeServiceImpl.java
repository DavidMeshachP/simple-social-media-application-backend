package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LikeDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
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
            newLike.setId(likeDto.getId());
            User user = userRepository.findById(likeDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Post post = postRepository.findById(likeDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));
            newLike.setlPost(post);
            newLike.setlUser(user);
            likeRepository.save(newLike);

            likeDto.setId(newLike.getId());

            return likeDto;
            
        } catch (Exception e) {
            return null;
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
                return null;
            }
        } catch (Exception e) {
            return null;
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
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    
    
}

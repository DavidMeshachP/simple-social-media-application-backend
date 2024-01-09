package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.likeDto.LikeDto;
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
    public Likes addLike(LikeDto likeDto) {
        try {
            Likes newLike = new Likes();
            newLike.setId(likeDto.getId());
            User user = userRepository.findById(likeDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Post post = postRepository.findById(likeDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));
            newLike.setlPost(post);
            newLike.setlUser(user);
            return likeRepository.save(newLike);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Likes> getLike(Long id) {
        try {
            if(likeRepository.findById(id).isPresent()){
                return likeRepository.findLikesBylPost(postRepository.getReferenceById(id));
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
                likeRepository.delete(like);
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

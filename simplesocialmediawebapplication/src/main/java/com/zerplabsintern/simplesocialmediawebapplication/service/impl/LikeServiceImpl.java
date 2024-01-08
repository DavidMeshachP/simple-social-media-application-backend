package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.repository.LikeRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.PostRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Likes addLike(Likes like) {
        try {
            Likes newLike = new Likes();
            newLike.setId(like.getId());
            newLike.setlPost(postRepository.getReferenceById(like.getlPost().getId()));
            newLike.setlUser(userRepository.getReferenceById(like.getlUser().getId()));
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

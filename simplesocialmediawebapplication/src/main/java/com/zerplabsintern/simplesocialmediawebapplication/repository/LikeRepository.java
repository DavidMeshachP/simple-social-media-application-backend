package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    List<Likes> findLikesBylPost(Post lPost);
    
}

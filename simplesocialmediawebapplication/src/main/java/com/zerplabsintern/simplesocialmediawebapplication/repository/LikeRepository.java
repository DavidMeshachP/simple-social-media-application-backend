package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Query(value = "Select * from Likes where post_id = ?1", nativeQuery = true)
    List<Likes> findLikesBylPost(Post lPost);

    @Query(value = "Select * from Likes where post_id = ?1", nativeQuery = true)
    boolean existsBylPostId(Long id);
    
}

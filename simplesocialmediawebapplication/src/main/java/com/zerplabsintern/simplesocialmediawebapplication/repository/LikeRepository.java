package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    @Query(value = "Select * from Likes where post_id = ?1", nativeQuery = true)
    List<Likes> findLikesBylPost(Long id);

    // @Modifying
    // @Query(value = "delete from Likes where post_id = ?1", nativeQuery = true)
    // void deletebyPostId(Long id );

    @Modifying
    @Query(value = "delete from Likes where post_id = ?1", nativeQuery = true)
    void deleteByPost_Id(Long postId);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "Select * from Comments where post_id = ?1", nativeQuery = true)
    List<Comment> findBycPost_Id(Long postId);

    // @Modifying
    // @Query(value = "delete from Comments where post_id = ?1", nativeQuery = true)
    // void deletebyPostId( Long id );

    @Modifying
    @Query(value = "delete from Comments where post_id = ?1", nativeQuery = true)
    void deleteByPost_Id(Long postId);
    
} 

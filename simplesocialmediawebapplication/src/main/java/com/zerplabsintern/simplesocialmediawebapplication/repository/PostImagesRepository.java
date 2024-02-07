package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;

public interface PostImagesRepository extends JpaRepository< PostImages, Long > {

    @Query(value = "select * from posts_images where post_id = ?1", nativeQuery = true)
    List<PostImages> findbyPostId(Long id);

    // @Modifying
    // @Query(value = "delete from posts_images where post_id = ?1", nativeQuery = true)
    // void deleteByPostId(Long id);

    void deleteByPost_Id(Long postId);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    
}

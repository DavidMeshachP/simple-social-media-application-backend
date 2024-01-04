package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
}

package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    
} 

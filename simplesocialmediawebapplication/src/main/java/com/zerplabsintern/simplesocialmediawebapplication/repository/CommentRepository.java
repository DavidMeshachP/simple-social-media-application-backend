package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByPostId(Long id);

    
} 

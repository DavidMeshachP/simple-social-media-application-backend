package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Post;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    // @Query("select u from Post u where u.pUser.id = ?1")
    @Query(value = "select * from posts where user_id = ?1", nativeQuery = true)
    List<Post> findBypUser_Id(Long userId);
    
}

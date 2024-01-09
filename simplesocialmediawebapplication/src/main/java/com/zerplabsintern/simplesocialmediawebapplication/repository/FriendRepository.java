package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long > {

    List<Friend> getFriendsByUserId(Long id);
    
}

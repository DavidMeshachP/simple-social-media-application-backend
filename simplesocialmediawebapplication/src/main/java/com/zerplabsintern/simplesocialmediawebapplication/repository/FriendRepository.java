package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long > {

    List<Friend> findByfUser_Id(Long id);

    boolean existsByfUser_Id(Long userId);

    Friend findFriendIdByfUser_Id(Long userId);
    
}

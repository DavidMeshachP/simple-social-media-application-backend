package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long > {

    @Query(value = "Select * from Friends where user_id = ?1", nativeQuery = true)
    List<Friend> findByfUser_Id(Long id);

    @Query(value = "select * from Friends where friend_id = ?1", nativeQuery = true)
    List<Friend> findByfFriend_id(Long id );

    @Query(value = "Select Exists(Select * from Friends where user_id = ?1)", nativeQuery = true)
    boolean existsByfUser_Id(Long userId);

    @Query(value = "Select friend_id from Friends where user_id = ?1", nativeQuery = true)
    Friend findFriendIdByfUser_Id(Long userId);
    
}

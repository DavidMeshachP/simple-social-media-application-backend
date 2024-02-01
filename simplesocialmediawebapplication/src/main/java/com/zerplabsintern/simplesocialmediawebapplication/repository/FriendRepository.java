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

    @Query(value = "Select * from Friends where user_id = ?1 and friend_id = ?2", nativeQuery = true)
    Friend existsByfUser_Id(Long userId, Long friendId);

    @Query(value = "Select id from Friends where user_id = ?1 and friend_id = ?2", nativeQuery = true)
    Long findIdByfUser_idfFriend_id(Long userId, Long friendId);
    
}

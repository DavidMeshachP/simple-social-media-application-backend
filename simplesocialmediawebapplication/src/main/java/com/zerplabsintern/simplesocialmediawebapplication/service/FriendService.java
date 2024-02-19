package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.FriendDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendService {

    FriendDto addFriend(FriendDto friendDto, UserDetails currentUser);

    List<Friend> getFriends(Long id);

    boolean deleteFriends(Long userId, Long friendId, UserDetails currentUser);
    
}

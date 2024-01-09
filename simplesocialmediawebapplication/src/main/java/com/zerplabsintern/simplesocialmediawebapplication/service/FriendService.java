package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendService {

    Friend addFriend(Friend friend);

    List<Friend> getFriends(Long id);

    boolean deleteFriends(Long userId, Long friendId);
    
}

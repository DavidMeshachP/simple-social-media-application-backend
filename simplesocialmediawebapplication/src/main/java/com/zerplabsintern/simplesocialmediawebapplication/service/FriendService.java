package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.FriendDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;

public interface FriendService {

    FriendDto addFriend(FriendDto friendDto);

    List<Friend> getFriends(Long id);

    boolean deleteFriends(Long userId, Long friendId);
    
}

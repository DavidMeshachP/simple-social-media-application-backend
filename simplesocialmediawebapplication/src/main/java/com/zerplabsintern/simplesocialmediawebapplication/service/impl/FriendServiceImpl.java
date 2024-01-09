package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;
import com.zerplabsintern.simplesocialmediawebapplication.repository.FriendRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public Friend addFriend(Friend friend) {
        try {
            return friendRepository.save(friend);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteFriends(Long userId, Long friendId) {
        if()
        return false;
    }

    @Override
    public List<Friend> getFriends(Long id) {
        try {
            return friendRepository.getFriendsByUserId(id);
        } catch (Exception e) {
            return null;
        }
    }

    
    
}

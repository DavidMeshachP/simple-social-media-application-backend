package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.FriendDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Friend;
import com.zerplabsintern.simplesocialmediawebapplication.exception.FriendServiceException;
import com.zerplabsintern.simplesocialmediawebapplication.repository.FriendRepository;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FriendDto addFriend(FriendDto friendDto) {
        try {

            Friend newFriend = new Friend();

            if(friendDto.getUserId() != null ) {

                newFriend.setfUser(userRepository.getReferenceById(friendDto.getUserId()));
            }
            else {
                throw new FriendServiceException("userId field cannot be empty, check the details that was sent..");
            }

            if(friendDto.getFriendId() != null ) {

                newFriend.setfUser2(userRepository.getReferenceById(friendDto.getFriendId()));
            }
            else {
                throw new FriendServiceException("friendId field cannot be empty, update the details that was sent.");
            }

            if(friendDto.getStatus() != null ) {
                
                newFriend.setStatus(friendDto.getStatus());
            }
            else {
                throw new FriendServiceException("status of the friend cannot be null, update the field..");
            }
            
            friendRepository.save(newFriend);

            friendDto.setId(newFriend.getId());

            return friendDto;
        } catch (Exception e) {
            throw new FriendServiceException("exception occured while trying to save Friends....");
        }
    }

    @Override
    public boolean deleteFriends(Long userId, Long friendId) {
        try {
            if(friendRepository.existsByfUser_Id(userId)){
                friendRepository.deleteById(friendRepository.findFriendIdByfUser_Id(userId).getId());
                return true;
            }
            else{
                throw new FriendServiceException("the combination of user id and friend id is not present.. ");
            }
        } catch (Exception e) {
            throw new FriendServiceException("exception occured while trying to save friends...");
        }
    }

    @Override
    public List<Friend> getFriends(Long id) {
        try {

            List<Friend> friends = friendRepository.findByfUser_Id(id);

            if( friends == null ) {
                List<Friend> friends2 = friendRepository.findByfFriend_id(id);

                if( friends2 == null ) {
                    return null;
                }
                else {
                    return friends2;
                }
            }
            else {
                return friends;
            }

        } catch (Exception e) {
            throw new FriendServiceException("exception occured when trying to get the friends...");
        }
    }

    
    
}

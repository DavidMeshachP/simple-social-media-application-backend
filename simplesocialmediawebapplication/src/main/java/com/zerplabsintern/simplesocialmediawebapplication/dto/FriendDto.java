package com.zerplabsintern.simplesocialmediawebapplication.dto;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class FriendDto {

    private Long id;

    private Long userId;

    private Long friendId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public FriendDto() {
        
    }    

    public FriendDto(Long id, Long userId, Long friendId, Status status) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }   
    
}

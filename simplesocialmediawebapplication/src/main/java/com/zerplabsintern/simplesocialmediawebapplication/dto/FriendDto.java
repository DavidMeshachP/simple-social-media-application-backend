package com.zerplabsintern.simplesocialmediawebapplication.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

enum Status {
    Accepted,
    Rejected,
    Pending
}

@Component
public class FriendDto {

    private Long id;

    private Long userId;

    private Long FriendId;

    @Enumerated(EnumType.STRING)
    private String status;

    public FriendDto() {
        
    }

    

    public FriendDto(Long id, Long userId, Long friendId, String status) {
        this.id = id;
        this.userId = userId;
        FriendId = friendId;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return FriendId;
    }

    public void setFriendId(Long friendId) {
        FriendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   
    
}

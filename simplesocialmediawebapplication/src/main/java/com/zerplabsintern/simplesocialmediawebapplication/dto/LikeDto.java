package com.zerplabsintern.simplesocialmediawebapplication.dto;

import org.springframework.stereotype.Component;

@Component
public class LikeDto {

    private long id;
    private long userId;
    private long postId;

    public LikeDto () {

    }

    public LikeDto(long id, long userId, long postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getPostId() {
        return postId;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
    
    
}

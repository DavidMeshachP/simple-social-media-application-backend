package com.zerplabsintern.simplesocialmediawebapplication.dto;

import com.zerplabsintern.simplesocialmediawebapplication.enums.LikeForType;
import com.zerplabsintern.simplesocialmediawebapplication.enums.LikeType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LikeDto {

    private long id;

    private long userId;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    @Enumerated(EnumType.STRING)
    private LikeForType likeForType;

    private long postId;

    private long commentId;

    public LikeDto () {

    }

    public LikeDto(long id, long userId, LikeType likeType, LikeForType likeForType, long postId, long commentId) {
        this.id = id;
        this.userId = userId;
        this.likeType = likeType;
        this.likeForType = likeForType;
        this.postId = postId;
        this.commentId = commentId;
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

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public LikeType getLikeType() {
        return likeType;
    }

    public void setLikeType(LikeType likeType) {
        this.likeType = likeType;
    }

    public LikeForType getLikeForType() {
        return likeForType;
    }

    public void setLikeForType(LikeForType likeForType) {
        this.likeForType = likeForType;
    }
    
}

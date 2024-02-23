package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class LikeDto {

    private long id;
    private long userId;
    private long postId;
    private long commentId;

    public LikeDto () {

    }
    
    public LikeDto(long id, long userId, long postId, long commentId) {
        this.id = id;
        this.userId = userId;
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
    
}

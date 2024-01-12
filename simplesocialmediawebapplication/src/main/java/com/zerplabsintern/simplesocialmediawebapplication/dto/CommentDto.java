package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class CommentDto {

    private Long id;

    private Long userId;

    private Long postId;

    private String comment;

    public CommentDto() {

    }

    public CommentDto(Long id, Long userId, Long postId, String comment) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
}

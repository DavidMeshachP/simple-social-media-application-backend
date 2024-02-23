package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class CommentDto {

    private Long id;

    private Long userId;

    private String type;

    private Long postId;

    private Long commentId;

    private String comment;

    public CommentDto() {

    }

    public CommentDto(Long id, Long userId, String type, Long postId, Long commentId, String comment) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.postId = postId;
        this.commentId = commentId;
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}

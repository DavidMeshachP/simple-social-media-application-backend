package com.zerplabsintern.simplesocialmediawebapplication.dto;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Mode;

public class PostDto {

    private Long id;

    private Long postId;

    private Long userId;

    private Mode mode;

    private String caption;

    public PostDto() {
        
    }    

    public PostDto(Long id, Long postId, Long userId, Mode mode, String caption) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.mode = mode;
        this.caption = caption;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
}

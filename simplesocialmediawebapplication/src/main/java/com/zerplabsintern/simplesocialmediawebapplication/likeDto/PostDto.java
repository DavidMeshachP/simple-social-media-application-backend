package com.zerplabsintern.simplesocialmediawebapplication.likeDto;

import org.springframework.stereotype.Component;

@Component
public class PostDto {

    private Long id;

    private Long postId;

    private Long userId;

    public PostDto() {
        
    }

    public PostDto(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
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

    
    
}

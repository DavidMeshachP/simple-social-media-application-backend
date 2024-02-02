package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class PostImagesDto {

    private Long id;

    private Long postId;

    private String image;

    public PostImagesDto() {

    }

    public PostImagesDto(Long id, Long postId, String image) {
        this.id = id;
        this.postId = postId;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}

package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class PostImagesDto {

    private Long id;

    private int postId;

    private String image;

    public PostImagesDto() {

    }

    public PostImagesDto(Long id, int postId, String image) {
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}

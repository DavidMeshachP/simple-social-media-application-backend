package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class PostImagesDto {

    private Long id;

    private int postId;

    private byte[] image;

    public PostImagesDto() {

    }

    public PostImagesDto(Long id, int postId, byte[] image) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    
}

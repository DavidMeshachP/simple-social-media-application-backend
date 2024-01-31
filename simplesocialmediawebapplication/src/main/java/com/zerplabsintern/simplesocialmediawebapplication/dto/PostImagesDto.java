package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class PostImagesDto {

    private Long id;

    private Long postId;

    private byte[] image;

    public PostImagesDto() {

    }

    public PostImagesDto(Long id, Long postId, byte[] image) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    
}

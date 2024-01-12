package com.zerplabsintern.simplesocialmediawebapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_images")

public class PostImages {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post pIPost;

    @Column(name = "image")
    private String image;
    
    public PostImages() {

    }

    public PostImages(Long id, Post pIPost, String image) {
        this.id = id;
        this.pIPost = pIPost;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getpIPost() {
        return pIPost;
    }

    public void setpIPost(Post pIPost) {
        this.pIPost = pIPost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

}

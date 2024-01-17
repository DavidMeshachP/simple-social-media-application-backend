package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User lUser;
   
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post lPost;

    @Column(name = "created")
    @CreationTimestamp
    private ZonedDateTime created;

    @Column(name = "modified")
    @UpdateTimestamp
    private ZonedDateTime modified;

    public Likes() {
        
    }

    public Likes(Long id, User lUser, Post lPost, ZonedDateTime createdOn, ZonedDateTime modifiedOn) {
        this.id = id;
        this.lUser = lUser;
        this.lPost = lPost;
        this.created = createdOn;
        this.modified = modifiedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getlUser() {
        return lUser;
    }

    public void setlUser(User lUser) {
        this.lUser = lUser;
    }

    public Post getlPost() {
        return lPost;
    }

    public void setlPost(Post lPost) {
        this.lPost = lPost;
    }

    public ZonedDateTime getCreatedOn() {
        return created;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.created = createdOn;
    }

    public ZonedDateTime getModifiedOn() {
        return modified;
    }

    public void setModifiedOn(ZonedDateTime modifiedOn) {
        this.modified = modifiedOn;
    }

    

}

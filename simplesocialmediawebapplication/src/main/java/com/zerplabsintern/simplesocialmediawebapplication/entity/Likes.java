package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zerplabsintern.simplesocialmediawebapplication.enums.LikeForType;
import com.zerplabsintern.simplesocialmediawebapplication.enums.LikeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment lComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "like_type")
    private LikeType likeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "like_for_type")
    private LikeForType likeForType;

    @Column(name = "created")
    @CreationTimestamp
    private ZonedDateTime created;

    @Column(name = "modified")
    @UpdateTimestamp
    private ZonedDateTime modified;

    public Likes() {
        
    }

    public Likes(Long id, User lUser, Post lPost, Comment lComment, LikeType likeType, LikeForType likeForType,
            ZonedDateTime created, ZonedDateTime modified) {
        this.id = id;
        this.lUser = lUser;
        this.lPost = lPost;
        this.lComment = lComment;
        this.likeType = likeType;
        this.likeForType = likeForType;
        this.created = created;
        this.modified = modified;
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

    public Comment getlComment() {
        return lComment;
    }

    public void setlComment(Comment lComment) {
        this.lComment = lComment;
    }

    public LikeType getLikeType() {
        return likeType;
    }

    public void setLikeType(LikeType likeType) {
        this.likeType = likeType;
    }

    public LikeForType getLikeForType() {
        return likeForType;
    }

    public void setLikeForType(LikeForType likeForType) {
        this.likeForType = likeForType;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getModified() {
        return modified;
    }

    public void setModified(ZonedDateTime modified) {
        this.modified = modified;
    }

}

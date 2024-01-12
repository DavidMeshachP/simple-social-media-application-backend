package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.LocalDateTime;

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
@Table(name = "comments")
public class Comment {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User cUser;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post cPost;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;

    public Comment() {

    }

    public Comment(User cUser, Post cPost, Long id, String comment, LocalDateTime created, LocalDateTime modified) {
        this.cUser = cUser;
        this.cPost = cPost;
        this.id = id;
        this.comment = comment;
        this.created = created;
        this.modified = modified;
    }

    public User getcUser() {
        return cUser;
    }

    public void setcUser(User cUser) {
        this.cUser = cUser;
    }

    public Post getcPost() {
        return cPost;
    }

    public void setcPost(Post cPost) {
        this.cPost = cPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    
    
}

package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Mode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

    @OneToMany(mappedBy = "lPost")
    private List<Likes> likes;

    @OneToMany(mappedBy = "cPost")
    private List<Comment> comments;

    @OneToMany(mappedBy = "pIPost")
    private List<PostImages> postImages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User pUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "user_id")
    // private int userId;

    @Column(name = "caption")
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private Mode mode;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private ZonedDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private ZonedDateTime modified;

    public Post() {

    }

    public Post(List<Likes> likes, List<Comment> comments, List<PostImages> postImages, User pUser, Long id,
            String caption, Mode mode, ZonedDateTime created, ZonedDateTime modified) {
        this.likes = likes;
        this.comments = comments;
        this.postImages = postImages;
        this.pUser = pUser;
        this.id = id;
        this.caption = caption;
        this.mode = mode;
        this.created = created;
        this.modified = modified;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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

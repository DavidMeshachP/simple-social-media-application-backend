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

@Entity
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

    // @Column(name = "post_id")
    // private int postId;

    // @Column(name = "user_id")
    // private int userId;

    @Column( name = "comment")
    private String comments;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;
    
}

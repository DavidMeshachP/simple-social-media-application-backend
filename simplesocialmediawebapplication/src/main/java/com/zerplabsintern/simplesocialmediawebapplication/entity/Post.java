package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

enum Mode {
    Public,
    Private
}

@Entity
public class Post {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User pUser;

    @OneToMany(mappedBy = "lpost")
    private List<Like> likes;

    @OneToMany(mappedBy = "cpost")
    private List<Comment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "image")
    private String image;

    @Column(name = "caption")
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private String mode;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;
    
}

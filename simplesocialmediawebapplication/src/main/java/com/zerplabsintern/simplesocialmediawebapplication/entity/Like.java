package com.zerplabsintern.simplesocialmediawebapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Like {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User lUser;
   
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post lPost;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "user_id")
    private int userId;

    @Column( name = "post_id")
    private int postId;

}

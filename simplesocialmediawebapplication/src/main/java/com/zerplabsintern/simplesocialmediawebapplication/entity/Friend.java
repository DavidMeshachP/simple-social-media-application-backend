package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Status;

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
@Table(name = "friends")
public class Friend {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User fUser;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User fUser2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @CreationTimestamp
    @Column(name = "created")
    private ZonedDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private ZonedDateTime modified;

    public Friend() {

    }

    public Friend(User fUser, User fUser2, Long id, Status status, ZonedDateTime created, ZonedDateTime modified) {
        this.fUser = fUser;
        this.fUser2 = fUser2;
        this.id = id;
        this.status = status;
        this.created = created;
        this.modified = modified;
    }

    public User getfUser() {
        return fUser;
    }

    public void setfUser(User fUser) {
        this.fUser = fUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public User getfUser2() {
        return fUser2;
    }

    public void setfUser2(User fUser2) {
        this.fUser2 = fUser2;
    }

        
    
}

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

enum Gender {
    Male,
    Female
}

@Entity
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "fUser")
    private List<Friend> friends;

    @OneToMany(mappedBy = "pUser")
    private List<Post> posts;

    @OneToMany(mappedBy = "lUser")
    private List<Like> likes;

    @OneToMany(mappedBy = "cUser")
    private List<Comment> comments;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email_id", unique = true, length = 40)
    private String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "bio" )
    private String bio;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    private LocalDateTime modifiedOn;

    public User() {

    }

    public User(Long id, String name, String emailId, String gender, String age, String bio, String password,
            LocalDateTime createdOn, LocalDateTime modifiedOn) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
        this.bio = bio;
        this.password = password;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    

}

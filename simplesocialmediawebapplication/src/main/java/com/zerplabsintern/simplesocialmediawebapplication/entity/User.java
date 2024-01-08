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

    @OneToMany(mappedBy = "userId")
    private List<Post> posts;

    @OneToMany(mappedBy = "lUser")
    private List<Likes> likes;

    @OneToMany(mappedBy = "cUser")
    private List<Comment> comments;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "email_id", unique = true, length = 40, nullable = false)
    private String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "bio" )
    private String bio;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "image")
    private String image;
    
    @Column(name = "created_on", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @UpdateTimestamp
    private LocalDateTime modifiedOn;


    public User() {

    }

    public User(List<Friend> friends, List<Post> posts, List<Likes> likes, List<Comment> comments, Long id, String name, String emailId, Gender gender, String age, String bio, String password, String image,
            LocalDateTime createdOn, LocalDateTime modifiedOn) {
        this.friends = friends;
        this.posts = posts;
        this.likes = likes;
        this.comments = comments;
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.age = age;
        this.bio = bio;
        this.password = password;
        this.image = image;
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

    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

}

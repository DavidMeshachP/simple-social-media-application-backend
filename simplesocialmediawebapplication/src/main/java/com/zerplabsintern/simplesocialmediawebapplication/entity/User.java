package com.zerplabsintern.simplesocialmediawebapplication.entity;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @OneToMany(mappedBy = "fUser")
    private List<Friend> friends;

    @OneToMany(mappedBy = "fUser2")
    private List<Friend> friends2;

    @OneToMany(mappedBy = "pUser")
    private List<Post> posts;

    @OneToMany(mappedBy = "lUser")
    private List<Likes> likes;

    @OneToMany(mappedBy = "cUser")
    private List<Comment> comments;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "description" )
    private String description;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "created")
    @CreationTimestamp
    private ZonedDateTime createdOn;

    @Column(name = "modified")
    @UpdateTimestamp
    private ZonedDateTime modifiedOn;


    public User() {

    }

    public User(List<Friend> friends, List<Friend> friends2, List<Post> posts, List<Likes> likes,
            List<Comment> comments, Long id, String name, String emailId, Gender gender, byte[] image, Date dateOfBirth,
            String description, String password, boolean isActive, ZonedDateTime createdOn, ZonedDateTime modifiedOn) {
        this.friends = friends;
        this.friends2 = friends2;
        this.posts = posts;
        this.likes = likes;
        this.comments = comments;
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.password = password;
        this.isActive = isActive;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(ZonedDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }   

}

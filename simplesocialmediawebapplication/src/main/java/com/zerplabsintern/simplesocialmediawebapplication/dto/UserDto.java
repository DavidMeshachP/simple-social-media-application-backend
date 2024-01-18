package com.zerplabsintern.simplesocialmediawebapplication.dto;

import java.sql.Date;

import com.zerplabsintern.simplesocialmediawebapplication.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDto {

    private Long id;

    private String name;

    private String emailId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String image;

    private Date dateOfBirth;

    private String description;

    private String password;

    public UserDto() {
        
    }

    public UserDto(Long id, String name, String emailId, Gender gender, String image, Date dateOfBirth,
            String description, String password) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.image = image;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    
}

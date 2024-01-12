package com.zerplabsintern.simplesocialmediawebapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.PostImages;

public interface PostImagesRepository extends JpaRepository< PostImages, Long > {
    
}

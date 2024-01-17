package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserRepository extends JpaRepository< User , Long >{

    // @Query()
    List<User> findByName(String name);
    
}

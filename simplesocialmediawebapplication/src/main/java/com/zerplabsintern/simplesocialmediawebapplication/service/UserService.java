package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserService {

    User save(User user);

    User updateUser(Long id, User user);

    User deleteUser(Long id);

    User getUser(Long id);

    List<User> getAllUser(String name);
    
}

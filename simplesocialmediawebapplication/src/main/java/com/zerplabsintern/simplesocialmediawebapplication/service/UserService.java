package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserService {

    User save(User user);

    User updateUser(Long id, User user);
    
}

package com.zerplabsintern.simplesocialmediawebapplication.service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserService {

    boolean save(User user);

    boolean updateUser(Long id, User user);
    
}

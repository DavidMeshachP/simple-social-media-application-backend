package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    boolean deleteUser(Long id);

    User getUser(Long id);

    List<User> getAllUser(String name);
    
}

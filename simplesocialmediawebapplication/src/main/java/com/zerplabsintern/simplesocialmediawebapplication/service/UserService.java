package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto, UserDetails currentUser);

    boolean deleteUser(Long id, UserDetails currentUser);

    User getUser(Long id);

    List<User> getAllUser(String name);

    String findByEmailId(String email);

    User findUserByEmailId(String email);
    
}

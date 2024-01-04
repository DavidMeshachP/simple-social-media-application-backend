package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean updateUser(Long id, User user) {
        try {

            if (userRepository.findById(id).isPresent()) {

                userRepository.save(user);

            }
            else {
                return false;
            }

        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
     
}
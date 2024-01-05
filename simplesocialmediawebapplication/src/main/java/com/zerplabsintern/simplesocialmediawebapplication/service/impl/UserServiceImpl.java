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
    public User save(User user) {
        userRepository.save(user);
        
        return userRepository.getReferenceById(user.getId());
    }

    @Override
    public User updateUser(Long id, User user) {
        try {

            if (userRepository.findById(id).isPresent()) {

                userRepository.save(user);

            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            return null;
        }
        return userRepository.getReferenceById(id);
    }
     
}
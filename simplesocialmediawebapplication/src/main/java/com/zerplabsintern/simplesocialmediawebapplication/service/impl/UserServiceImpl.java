package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.List;

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

        if(userRepository.findById(user.getId()).isPresent()){
            updateUser(user.getId(), user);
        }
        else{
            userRepository.save(user);
        }
        
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

    @Override
    public User deleteUser(Long id) {
        
        try {

            if (userRepository.findById(id).isPresent()) {

                userRepository.deleteById(id);

            }
            else {
                return null;
            }
            
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @Override
    public User getUser(Long id) {
        
        if(userRepository.findById(id).isPresent()){
            return userRepository.getReferenceById(id);
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> getAllUser(String name) {
        return userRepository.findByName(name);
    } 
     
}
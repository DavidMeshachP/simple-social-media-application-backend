package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.repository.UserRepository;
import com.zerplabsintern.simplesocialmediawebapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {

        User newUser = new User();

        newUser.setDateOfBirth(userDto.getDateOfBirth());
        newUser.setDescription(userDto.getDescription());
        newUser.setGender(userDto.getGender());
        newUser.setEmailId(userDto.getEmailId());
        newUser.setName(userDto.getName());
        newUser.setPassword(userDto.getPassword());
        newUser.setImage(Base64.getDecoder().decode(userDto.getImage()));

        Long id = userRepository.findIdbyemailId(userDto.getEmailId());

        if(id != null){
            return null;
        }
        else{
            userRepository.save(newUser);
        }
        
        return userDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        try {

            if (userRepository.findById(id).isPresent()) {

                User newUser = userRepository.getReferenceById(id);

                if(userDto.getId() != null) {
                    newUser.setId(id);
                }
                if(userDto.getDateOfBirth() !=null){
                    newUser.setDateOfBirth(userDto.getDateOfBirth());
                }
                if(userDto.getDescription() !=null) {
                    newUser.setDescription(userDto.getDescription());
                }
                if(userDto.getGender() !=null) {
                    newUser.setGender(userDto.getGender());
                }
                if(userDto.getName() != null ){
                    newUser.setName(userDto.getName());
                }
                if(userDto.getPassword() !=null) {
                    newUser.setPassword(userDto.getPassword());
                }
                if(userDto.getImage() != null) {
                    newUser.setImage(Base64.getDecoder().decode(userDto.getImage()));
                }

                userRepository.save(newUser);

            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            return null;
        }
        return userDto;
    }

    @Override
    public boolean deleteUser(Long id) {
        
        try {

            if (userRepository.findById(id).isPresent()) {

                userRepository.deleteById(id);
                return true;
            }
            else {
                return false;
            }
            
        } catch (Exception e) {
            return false;
        }
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
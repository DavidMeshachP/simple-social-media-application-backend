package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.dto.UserDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.User;
import com.zerplabsintern.simplesocialmediawebapplication.exception.UserServiceException;
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
            throw new UserServiceException("User already exists, cannot update..");
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
                throw new UserServiceException("User not found to update, try updating a different user..");
            }

        }
        catch (Exception e) {
            throw new UserServiceException(e.getMessage());
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
                throw new UserServiceException("User not found to delete...");
            }
            
        } catch (Exception e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public User getUser(Long id) {
        
        if(userRepository.findById(id).isPresent()){
            return userRepository.getReferenceById(id);
        }
        else{
            throw new UserServiceException("No user Present by this id.... ");
        }
    }

    @Override
    public List<User> getAllUser(String name) {

        List<User> users = userRepository.findByName(name);

        if(users.size() == 0 ) {
            throw new UserServiceException("no user present with this name..");
        }

        else {
            return users;
        }
    } 

    @Override
    public String findByEmailId(String email) {

        Long id = userRepository.findIdbyemailId(email);

        if ( id != null ) {
            
            return userRepository.getReferenceById(id).getEmailId();
        }

        else {
            throw new UserServiceException("No user By this email Id ");
        }


    }

    @Override
    public User findUserByEmailId(String email) {
        
        Long id = userRepository.findIdbyemailId(email);

        Optional<User> user = userRepository.findById(id);
         
        if(user.isPresent()) {
            
            return user.get();
        }
        else {
            throw new UserServiceException("No User found ..");
        }

    }

    
     
}
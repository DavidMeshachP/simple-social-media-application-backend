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

        if( userDto.getDateOfBirth() != null ){

            newUser.setDateOfBirth(userDto.getDateOfBirth());
        }
        else {
            throw new UserServiceException("date of birth field cannot be null, check the details that was sent again..");
        }

        newUser.setDescription(userDto.getDescription());

        if( userDto.getGender() != null ) {

            newUser.setGender(userDto.getGender());
        }
        else {
            throw new UserServiceException("gender field cannot be null, check the details that was sent again..");
        }

        if( userDto.getEmailId() != null ) {

            newUser.setEmailId(userDto.getEmailId());
        }
        else {
            throw new UserServiceException("email_id field cannot be null, check the details that was sent again..");
        }

        if( userDto.getName() != null ) {

            newUser.setName(userDto.getName());
        }
        else {
            throw new UserServiceException("name field cannot be null, check the details that was sent again..");
        }

        if( userDto.getPassword() != null ) {

            if(userDto.getPassword().length() > 20 ) {
                throw new UserServiceException("password length more than the limit reduce it to less than 20 ");
            }
            else {
                
                newUser.setPassword(userDto.getPassword());
            }

        }
        else {
            throw new UserServiceException("password field cannot be null, check the details that was sent again..");
        }

        newUser.setActive(true);

        newUser.setImage(Base64.getDecoder().decode(userDto.getImage()));

        Long id = userRepository.findIdbyemailId(userDto.getEmailId());

        if(id != null){
            throw new UserServiceException("User already exists, cannot insert a user.."); 
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
                else {
                    throw new UserServiceException("id cannot be null, check the data that was sent again..");
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

                if( userRepository.findById(id).get().isActive() == true && userDto.isActive() == false ) {

                    newUser.setActive(false);

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

                UserDto userDto = new UserDto();

                userDto.setId(id);
                userDto.setActive(false);

                updateUser(id, userDto);

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
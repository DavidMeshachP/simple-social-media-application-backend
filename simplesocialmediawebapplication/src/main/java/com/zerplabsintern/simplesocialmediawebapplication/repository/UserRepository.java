package com.zerplabsintern.simplesocialmediawebapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zerplabsintern.simplesocialmediawebapplication.entity.User;

public interface UserRepository extends JpaRepository< User , Long >{

    // @Query("select u from users u where u.name = ?1")
    @Query(value = "select * from users where user_name = ?1", nativeQuery = true )
    List<User> findByName(String name);

    // @Query("select u.id from users u where u.emailId = ?1")
    @Query(value = "select id from users where email_id = ?1", nativeQuery = true)
    Long findIdbyemailId(String emailId);

    @Query(value = "select email_id from users where id = ?1", nativeQuery = true)
    String findEmailIdById(Long id);
    
}

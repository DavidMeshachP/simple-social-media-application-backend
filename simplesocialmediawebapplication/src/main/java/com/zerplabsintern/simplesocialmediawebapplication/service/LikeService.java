package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LikeDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;

public interface LikeService {

    LikeDto addLike(LikeDto likeDto, UserDetails currentUser);

    boolean removeLike(Likes like, UserDetails currentUser);

    List<LikeDto> getLike(Long id);

    boolean deleteByPostId(Long id);

}

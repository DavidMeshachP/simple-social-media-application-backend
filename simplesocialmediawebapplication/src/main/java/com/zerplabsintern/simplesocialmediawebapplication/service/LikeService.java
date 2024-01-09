package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;
import com.zerplabsintern.simplesocialmediawebapplication.likeDto.LikeDto;

public interface LikeService {

    ResponseEntity<?> addLike(LikeDto likeDto);

    boolean removeLike(Likes like);

    List<Likes> getLike(Long id);

}

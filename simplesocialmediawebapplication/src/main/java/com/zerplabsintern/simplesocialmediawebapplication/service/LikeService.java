package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.dto.LikeDto;
import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;

public interface LikeService {

    LikeDto addLike(LikeDto likeDto);

    boolean removeLike(Likes like);

    List<LikeDto> getLike(Long id);

}

package com.zerplabsintern.simplesocialmediawebapplication.service;

import java.util.List;

import com.zerplabsintern.simplesocialmediawebapplication.entity.Likes;

public interface LikeService {

    Likes addLike(Likes like);

    boolean removeLike(Likes like);

    List<Likes> getLike(Long id);

}

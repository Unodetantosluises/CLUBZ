package com.rungroup.web.service;

import com.rungroup.web.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
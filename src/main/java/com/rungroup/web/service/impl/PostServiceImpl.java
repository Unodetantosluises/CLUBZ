package com.rungroup.web.service.impl;

import com.rungroup.web.dto.PostDto;
import com.rungroup.web.mapper.PostMapper;
import com.rungroup.web.models.Post;
import com.rungroup.web.repository.PostRepository;
import com.rungroup.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.PostMapper.mapToPostDto;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){this.postRepository = postRepository;}

    @Override
    public List<PostDto> findAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedOnAsc();
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }
}

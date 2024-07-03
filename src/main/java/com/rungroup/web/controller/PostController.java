package com.rungroup.web.controller;

import com.rungroup.web.dto.PostDto;
import com.rungroup.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {this.postService = postService; }

    @GetMapping("/posts")
    public String listPots(Model model){
        List<PostDto>  posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "post-list";
    }
}

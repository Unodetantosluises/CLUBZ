package com.rungroup.web.controller;

import com.rungroup.web.dto.PostDto;
import com.rungroup.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {this.postService = postService; }

    @GetMapping("/posts")
    public String listPost(Model model){
        List<PostDto>  posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "post-list";
    }

    @GetMapping("/posts/search")
    public String searchPost(@RequestParam(value = "querry") String query, Model model) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "post-list";
    }
}

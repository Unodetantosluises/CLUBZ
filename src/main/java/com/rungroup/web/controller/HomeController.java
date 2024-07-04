package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.dto.EventDto;
import com.rungroup.web.dto.PostDto;
import com.rungroup.web.service.ClubService;
import com.rungroup.web.service.EventService;
import com.rungroup.web.service.PostService;
import com.rungroup.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ClubService clubService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<ClubDto> clubs = clubService.getAllClubs();
        List<EventDto> events = eventService.getAllEvents();
        List<PostDto> posts = postService.getAllPosts();

        ClubDto firstClub = !clubs.isEmpty() ? clubs.getFirst() : null;
        EventDto firstEvent = !events.isEmpty() ? events.getFirst() : null;
        PostDto firstPost = !posts.isEmpty() ? posts.getFirst() : null;

        model.addAttribute("clubs", clubs);
        model.addAttribute("events", events);
        model.addAttribute("posts", posts);

        model.addAttribute("club", firstClub);
        model.addAttribute("event", firstEvent);
        model.addAttribute("post", firstPost);

        return "home";
    }
}

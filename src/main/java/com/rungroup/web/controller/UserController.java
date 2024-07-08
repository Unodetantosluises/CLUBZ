package com.rungroup.web.controller;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
    private UserService userService;

    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping("/users/{userId}/edit")
    public String editClubForm(@PathVariable("userId") Long userId, Model model) {
        UserEntity user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/users/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long userId, @Valid @ModelAttribute("user") UserDto user, BindingResult result){
        if(result.hasErrors()){
            return "user-edit";
        }
        user.setId(userId);
        userService.updateUser(user);
        return "redirect:/user-edit";
    }
}

package com.rungroup.web.controller;

import com.rungroup.web.dto.UserDto;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService){ this.userService = userService; }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(userDto.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with that email.");
        }

        UserEntity existingUserUsername = userService.findByUsername(userDto.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            result.rejectValue("username", null, "There is already an account registered with that username.");
        }
        if(userDto.getActive() == null) {
            userDto.setActive(true);
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/home?success";
    }
}

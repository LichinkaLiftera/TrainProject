package com.example.trainproject.controllers;

import com.example.trainproject.models.User;
import com.example.trainproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model map, Principal principal) {
        User tempUser = userService.findByUsername(principal.getName());
        map.addAttribute("user", tempUser);
        return "user-page";
    }
    @GetMapping("addTrain/")
    public String addTrain(){
        return "add-exercise-page";
    }

}

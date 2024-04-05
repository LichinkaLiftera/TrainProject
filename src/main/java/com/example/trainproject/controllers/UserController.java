package com.example.trainproject.controllers;

import com.example.trainproject.models.Exercise;
import com.example.trainproject.models.Train;
import com.example.trainproject.models.User;
import com.example.trainproject.service.ExerciseService;
import com.example.trainproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;



@Controller
@RequestMapping("user/")
public class UserController {
    private final UserService userService;
    private final ExerciseService exerciseService;

    @Autowired
    public UserController(UserService userService, ExerciseService exerciseService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public String getUser(Model map, Principal principal) {
        User tempUser = userService.findByUsername(principal.getName());
        Train tempTrain = new Train();
        List<Exercise> exerciseList = exerciseService.getAllExercises();
        map.addAttribute("user", tempUser);
        map.addAttribute("exercises", exerciseList);
        map.addAttribute("trains", tempTrain);
        return "add-exercise";
    }
    @GetMapping("/{id}")
    public String userId(@PathVariable("id") long id, Model map){
        map.addAttribute("id", userService.getUserById(id));
        return "user-page";
    }
//    @GetMapping("addTrain/{id}")
//    public String addTrain(@PathVariable("id") long id,  Model map){
//        List<Exercise> exerciseList = exerciseService.getAllExercises();
//        map.addAttribute("exercises", exerciseList);
//        map.addAttribute("user", userService.getUserById(id));
//        return "add-exercise-page";
//    }
    @PostMapping("add")
    public String recordTrain(@ModelAttribute("exercise") Exercise exercise){

        return "redirect:/user/";
    }


}

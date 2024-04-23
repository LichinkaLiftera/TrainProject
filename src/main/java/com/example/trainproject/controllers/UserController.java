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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
        map.addAttribute("user", userService.findByUsername(principal.getName()));
        return "user-page";
    }
    @GetMapping("/{id}")
    public String userId(@PathVariable("id") long id, Model map){
        map.addAttribute("id", userService.getUserById(id));
        return "add-exercise";
    }
    @GetMapping("addTrain/{id}")
    public String addTrain(@PathVariable("id") long id,  Model map){
        map.addAttribute("allExercises", exerciseService.getAllExercises());
        map.addAttribute("user", userService.getUserById(id).get());
        map.addAttribute("allTrains", userService.getUserById(id).get().getTrainList());
        map.addAttribute("train", new Train(LocalDate.now().toString()));
        map.addAttribute("exercise", new Exercise());
        return "add-exercise";
    }
    @PostMapping("/{id}")
    public String recordTrain(@PathVariable("id")long id,
                              @ModelAttribute("train") Train train,
                              @ModelAttribute("exercise") Exercise exercise){
        System.out.println(train.getDate());
        System.out.println(exercise.getName());
        System.out.println(exercise.getSets());
        System.out.println(exercise.getWeight());
        System.out.println(exercise.getRepetitions());
        return "redirect:addTrain/{id}";
    }


}

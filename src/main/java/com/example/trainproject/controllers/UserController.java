package com.example.trainproject.controllers;

import com.example.trainproject.models.Exercise;
import com.example.trainproject.models.Train;
import com.example.trainproject.models.User;
import com.example.trainproject.service.ExerciseService;
import com.example.trainproject.service.TrainService;
import com.example.trainproject.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("user/")
public class UserController {
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final TrainService trainService;

    @Autowired
    public UserController(UserService userService, ExerciseService exerciseService, TrainService trainService) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.trainService = trainService;
    }

    @GetMapping
    public String getUser(Model map, Principal principal) {
        map.addAttribute("user", userService.findByUsername(principal.getName()));
        map.addAttribute("allTrains", userService.findByUsername(principal.getName()).getTrainList());
        return "user-page";
    }

//    @GetMapping("/{id}")
//    public String userId(@PathVariable("id") long id, Model map) {
//        map.addAttribute("id", userService.getUserById(id));
//        return "add-exercise";
//    }

    @GetMapping("editTrain/{id}")
    public String editTrain(@PathVariable("id") long id, Model map) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User tempUser = userService.getUserById(id).get();
        Train tempTrain = new Train(LocalDate.now().toString());
        List<Exercise> tempExerciseList = tempTrain.getExercisesList();

        if (userService.haveTrainToday(tempUser, tempTrain.getDate())) {
            tempTrain = userService.getUserTrainByDate(tempUser, tempTrain.getDate());
            tempExerciseList = tempTrain.getExercisesList(gson, tempTrain.getExercises());
        }

        map.addAttribute("allExercises", exerciseService.getAllExercises());
        map.addAttribute("user", tempUser);
        map.addAttribute("train", tempTrain);
        map.addAttribute("exercise", new Exercise());
        map.addAttribute("userExercises", tempExerciseList);
        System.out.println("этидТрэйн");

        return "add-exercise";
    }

    @PostMapping("/{id}")
    public String recordTrain(@PathVariable("id") long id,
                              @ModelAttribute("train") Train train,
                              @ModelAttribute("exercise") Exercise exercise) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Train tempTrain = new Train(train.getDate());
        Exercise tempExercise = new Exercise(exercise.getId(), exercise.getName(),
                exercise.getWeight(), exercise.getSets(), exercise.getRepetitions());

        if (userService.haveTrainToday(userService.getUserById(id).get(), train.getDate())) {
            tempTrain = userService.getUserTrainByDate(userService.getUserById(id).get(), train.getDate());
            trainService.updateTrain(tempTrain, tempExercise, gson);
        } else {
            trainService.createTrain(tempTrain, tempExercise, gson);
            userService.getUserById(id).get().getTrainList().add(tempTrain);
            userService.updateUser(userService.getUserById(id).get());
        }
        System.out.println("пост айди");
        return "redirect:editTrain/{id}";
    }

    @PostMapping("delete/{id}/{del}")
    public String deleteTrain(@PathVariable("del") int del ,@PathVariable("id") long id) {
        System.out.println("делитПост" + " "+ del + " "+ id);
        return "redirect:editTrain/{id}";
    }
}

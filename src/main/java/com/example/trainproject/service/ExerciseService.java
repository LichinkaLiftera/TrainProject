package com.example.trainproject.service;

import com.example.trainproject.models.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ExerciseService {
    List<Exercise> getAllExercises();

}

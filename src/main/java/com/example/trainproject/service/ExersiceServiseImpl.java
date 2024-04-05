package com.example.trainproject.service;

import com.example.trainproject.dao.ExerciseDao;
import com.example.trainproject.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExersiceServiseImpl implements ExerciseService{
    private final ExerciseDao exerciseDao;

    @Autowired
    public ExersiceServiseImpl(ExerciseDao exerciseDao){
        this.exerciseDao = exerciseDao;
    }
    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDao.findAll();
    }
}

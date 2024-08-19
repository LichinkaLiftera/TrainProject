package com.example.trainproject.service;

import com.example.trainproject.models.Exercise;
import com.example.trainproject.models.Train;
import com.google.gson.Gson;

public interface TrainService {

    void createTrain(Train train, Exercise exercise, Gson gson);
    void updateTrain(Train train, Exercise exercise, Gson gson);
    void deleteExercise(Train train, Gson gson, int del);
    void deleteTrain(long ig);
    Train findByDate(String date);
}

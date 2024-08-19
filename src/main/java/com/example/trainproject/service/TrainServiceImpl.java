package com.example.trainproject.service;

import com.example.trainproject.dao.TrainDao;
import com.example.trainproject.models.Exercise;
import com.example.trainproject.models.Train;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService{

    private final TrainDao trainDao;

    @Autowired
    public TrainServiceImpl(TrainDao trainDao){
        this.trainDao = trainDao;
    }

    @Override
    public void createTrain(Train train, Exercise exercise, Gson gson) {
        train.setExercisesList(new ArrayList<>());
        train.getExercisesList().add(exercise);
        train.updateTonnage();
        train.setExercises(gson.toJson(train.getExercisesList()));
        trainDao.save(train);
    }

    @Override
    public void updateTrain(Train train, Exercise exercise, Gson gson) {
        train.setExercisesList(train.getExercisesList(gson,train.getExercises()));
        train.getExercisesList().add(exercise);
        train.updateTonnage();
        train.setExercises(gson.toJson(train.getExercisesList()));
        trainDao.save(train);
    }

    @Override
    public void deleteExercise(Train train, Gson gson, int del) {
        List<Exercise> tempList = train.getExercisesList(gson, train.getExercises());
        tempList.remove(del);
        train.setExercisesList(tempList);
        train.setExercises(gson.toJson(tempList));
        train.updateTonnage();
        trainDao.save(train);
    }

    @Override
    public void deleteTrain(long id) {
        trainDao.deleteById(id);
    }

    @Override
    public Train findByDate(String date) {
        return trainDao.getTrainByDate(date);
    }
}

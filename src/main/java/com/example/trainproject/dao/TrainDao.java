package com.example.trainproject.dao;

import com.example.trainproject.models.Exercise;
import com.example.trainproject.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TrainDao extends JpaRepository<Train, Long> {

    Train getTrainByDate(String date);
    Train getTrainById(long id);
}

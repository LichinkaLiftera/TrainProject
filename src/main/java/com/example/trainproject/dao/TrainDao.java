package com.example.trainproject.dao;

import com.example.trainproject.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainDao extends JpaRepository<Train, Long> {

    Train getTrainByDate(String date);
}

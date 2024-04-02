package com.example.trainproject.dao;

import com.example.trainproject.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseDao extends JpaRepository<Exercise, Long> {
}

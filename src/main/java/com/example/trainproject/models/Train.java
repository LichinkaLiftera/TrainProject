package com.example.trainproject.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "Trains")
public class Train {

    public Train(String date){
        this.date = date;
        this.exercisesList = new ArrayList<>();
        this.exercises = "-";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String date;
    @Transient
    private List<Exercise> exercisesList;

    @Column
    @JdbcTypeCode(SqlTypes.JSON)
    private String exercises;

    @Column
    private double tonnage;

    public void updateTonnage(){
        double sum = 0;
        for(Exercise ex: exercisesList){
            sum+= ex.getTonnage();
        }
        this.tonnage = sum;
    }

    public List<Exercise> getExercisesList(Gson gson, String jsonExercises) {
        return new ArrayList<>(gson.fromJson(
                jsonExercises, new TypeToken<ArrayList<Exercise>>(){}.getType()));
    }
}

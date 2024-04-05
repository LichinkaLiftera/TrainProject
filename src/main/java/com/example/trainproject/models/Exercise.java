package com.example.trainproject.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "Exercises")
public class Exercise {

    public Exercise(String name, int weight, int sets, int repetitions){
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.repetitions = repetitions;
        this.tonnage = weight * sets * repetitions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    private int sets;

    @Transient
    private int repetitions;

    @Transient
    private int weight;

    @Transient
    private int tonnage;

}

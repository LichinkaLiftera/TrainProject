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

    public Exercise(long id,String name, double weight, int sets, int repetitions){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.repetitions = repetitions;
        this.tonnage = sets * repetitions * weight;
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
    private double weight;

    @Transient
    private double tonnage;

}

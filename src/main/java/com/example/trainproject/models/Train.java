package com.example.trainproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    @Transient
    private int tonnage;

}

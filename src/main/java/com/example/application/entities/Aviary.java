package com.example.application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "aviaries")
@NoArgsConstructor
public class Aviary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "aviary_type")
    String type;

    @Column(name = "aviary_location")
    String location;

    @OneToMany(mappedBy = "aviary", cascade = CascadeType.ALL)
    Set<Animal> animals;

    public Aviary(String type, String location) {
        this.type = type;
        this.location = location;
    }
}

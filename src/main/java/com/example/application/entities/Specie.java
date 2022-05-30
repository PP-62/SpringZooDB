package com.example.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "animals_species")
@NoArgsConstructor
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "specie_type")
    String type;

    @Column(name = "specie_group")
    String group;

    @Column(name = "specie_life_style")
    String lifeStyle;

    @Column(name = "specie_status")
    String status;

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL)
    Set<Animal> animals;

    public Specie(String type, String group, String lifeStyle, String status) {
        this.type = type;
        this.group = group;
        this.lifeStyle = lifeStyle;
        this.status = status;
    }
}

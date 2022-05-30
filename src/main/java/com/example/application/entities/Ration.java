package com.example.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "animals_rations")
@NoArgsConstructor
public class Ration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "ration_type")
    String type;

    @Column(name = "feeding_per_day")
    Short feedingCount;

    @OneToMany(mappedBy = "ration", cascade = CascadeType.ALL)
    Set<Animal> animals;

    public Ration(String type, Short feedingCount) {
        this.type = type;
        this.feedingCount = feedingCount;
    }
}

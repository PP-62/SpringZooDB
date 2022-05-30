package com.example.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "animals")
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "animal_name")
    String name;

    @Column(name = "gender")
    Character gender;

    @Column(name = "appeared_date")
    Date appearedDate;

    @Column(name = "aviary_id", insertable = false, updatable = false)
    Long aviaryId;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "aviary_id")
    Aviary aviary;

    @Column(name = "specie_id", insertable = false, updatable = false)
    Long specieId;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "specie_id")
    Specie specie;

    @Column(name = "keeper_id", insertable = false, updatable = false)
    Long keeperId;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "keeper_id")
    Keeper keeper;

    @Column(name = "ration_id", insertable = false, updatable = false)
    Long rationId;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "ration_id")
    Ration ration;

    public Animal(String name, Character gender, Date appearedDate, Aviary aviary, Specie specie, Keeper keeper, Ration ration) {
        this.name = name;
        this.gender = gender;
        this.appearedDate = appearedDate;
        this.aviary = aviary;
        this.aviaryId = aviary.getId();
        this.specie = specie;
        this.specieId = specie.getId();
        this.keeper = keeper;
        this.keeperId = keeper.getId();
        this.ration = ration;
        this.rationId = ration.getId();
    }
}

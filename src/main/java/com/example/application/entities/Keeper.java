package com.example.application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "keepers")
@NoArgsConstructor
public class Keeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "keeper_name")
    String name;

//    @Column(name = "keeper_surname")
//    String surname;

    @Column(name = "keeper_rank")
    String rank;

    @Column(name = "keeper_birthday_date")
    Date birthday;

    @OneToMany(mappedBy = "keeper", cascade = CascadeType.ALL)
    Set<Animal> animals;

    public Keeper(String name, String rank, Date birthday) {
        this.name = name;
        this.rank = rank;
        this.birthday = birthday;
    }
}

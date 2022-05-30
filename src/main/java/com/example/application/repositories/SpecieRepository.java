package com.example.application.repositories;

import com.example.application.entities.Specie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecieRepository extends CrudRepository<Specie, Long> {

    @Query(value = "SELECT u FROM Specie u WHERE (u.type = ?1 or ?1 = '') and (u.group = ?2 or ?2 = '') and (u.lifeStyle = ?3 or ?3 = '') and (u.status = ?4 or ?4 = '')")
    List<Specie> findAllByAll(String type, String group, String lifeStyle, String status);
}

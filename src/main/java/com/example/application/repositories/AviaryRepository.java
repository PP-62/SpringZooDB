package com.example.application.repositories;

import com.example.application.entities.Aviary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AviaryRepository extends CrudRepository<Aviary,Long>{
    List<Aviary> findByType(String type);
    List<Aviary> findByLocation(String location);
    List<Aviary> findByTypeAndLocation(String type, String location);
}

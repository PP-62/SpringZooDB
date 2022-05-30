package com.example.application.repositories;

import com.example.application.entities.Keeper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeeperRepository extends CrudRepository<Keeper,Long> {
    List<Keeper> findByName(String name);
    List<Keeper> findByRank(String rank);
    List<Keeper> findByNameAndRank(String name, String rank);
}

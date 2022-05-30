package com.example.application.repositories;

import com.example.application.entities.Ration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RationRepository extends CrudRepository<Ration,Long> {
//    List<Aviary> findByType(String type);
//    List<Aviary> findByFeedingCount(String feedingCount);
//    List<Aviary> findByTypeAndFeedingCount(String type, String feedingCount);

    @Query(value = "SELECT u FROM Ration u WHERE (u.type = ?1 or ?1 = ' ') and (u.feedingCount = ?2 or ?2 = 0)")
    List<Ration> findAllByAll(String type, Short feedingCount);
}

package com.example.application.repositories;

import com.example.application.entities.Animal;
import com.example.application.entities.Aviary;
import com.example.application.entities.Specie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal,Long> {
//    List<Aviary> findByType(String type);
//    List<Aviary> findByLocation(String location);
//    List<Aviary> findByTypeAndLocation(String type, String location);
//@Query(value = "SELECT u FROM Animal u WHERE (u.name = ?1 or ?1 = '') and (u.gender = ?2 or ?2 = '') and (u.appearedDate = ?3 or ?3 = '') and (u.specieId = ?4 or ?4 = '') and (u.keeperId = ?5 or ?5 = '') and (u.aviaryId = ?6 or ?6 = '') and (u.rationId = ?7 or ?7 = '')")
@Query(value = "SELECT u FROM Animal u WHERE (u.name = ?1 or ?1 = '') and (u.gender = ?2 or ?2 = 'n') and (u.appearedDate = ?3 or ?3 = null) and (u.specieId = ?4 or ?4 = null) and (u.keeperId = ?5 or ?5 = null) and (u.aviaryId = ?6 or ?6 = null) and (u.rationId = ?7 or ?7 = null)")
List<Animal> findAllByAll(String name,
                          Character gender,
                          Date appearedDate,
                          Long specieId,
                          Long keeperId,
                          Long aviaryId,
                          Long rationId);
}

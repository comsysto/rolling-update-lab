package de.comsystoreply.rollingupdate.data.repository;

import de.comsystoreply.rollingupdate.data.entity.CoffeeFanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

@org.springframework.stereotype.Repository
public interface CoffeeFanRepository extends CrudRepository<CoffeeFanEntity, String> {

    @Query(value = "SELECT nextval('username_id_seq')", nativeQuery = true)
    long getNextId();

    Collection<CoffeeFanEntity> findByCupsDrunkEquals(int cupsDrunk);
}

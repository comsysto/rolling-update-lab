package de.comsystoreply.rollingupdate.service;

import de.comsystoreply.rollingupdate.data.entity.CoffeeFanEntity;
import de.comsystoreply.rollingupdate.data.entity.CoffeeType;
import de.comsystoreply.rollingupdate.data.repository.CoffeeFanRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Log4j2
public class CoffeeService {
    private final CoffeeFanRepository repository;

    public CoffeeService(CoffeeFanRepository repository) {
        this.repository = repository;
    }

    public Collection<CoffeeFanEntity> getFansWithConsumedCoffee(int cupsDrunk) {
        log.info("Querying by amount of cups drunk {}", cupsDrunk);
        return repository.findByCupsDrunkEquals(cupsDrunk);
    }

    public void consumeCoffee(String username, CoffeeType coffeeType) {
        log.info("Consuming coffee '{}' for username '{}'", coffeeType, username);
        CoffeeFanEntity entity = repository.findById(username).orElseThrow();
        int cupsDrunk = entity.getCupsDrunk();
        entity.setCupsDrunk(++cupsDrunk);
        repository.save(entity);
    }
}

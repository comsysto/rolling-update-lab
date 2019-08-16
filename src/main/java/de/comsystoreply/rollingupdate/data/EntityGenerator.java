package de.comsystoreply.rollingupdate.data;

import de.comsystoreply.rollingupdate.data.entity.CoffeeFanEntity;
import de.comsystoreply.rollingupdate.data.entity.CoffeeType;
import de.comsystoreply.rollingupdate.data.repository.CoffeeFanRepository;
import de.comsystoreply.rollingupdate.service.CoffeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j2
@Component
public class EntityGenerator {
    private final Random rand = new Random();
    private final CoffeeFanRepository repository;
    private final CoffeeService coffeeService;

    public EntityGenerator(CoffeeFanRepository repository, CoffeeService coffeeService) {
        this.repository = repository;
        this.coffeeService = coffeeService;
    }

    @Scheduled(fixedRate = 10_000)
    @Transactional
    public void generateEntity() {
        log.info("Generating entity");

        CoffeeFanEntity entity = new CoffeeFanEntity();
        entity.setUsername(createRandomUsername());
        entity.setCupsDrunk(0);
        entity.setFavouriteCoffee(getRandomCoffeeType());
        repository.save(entity);

        log.info("Entity persisted {}", entity);
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void consumeCoffee() {
        log.info("Consuming coffee for random person");

        List<CoffeeFanEntity> allEntities = new ArrayList<>();
        repository.findAll().forEach(allEntities::add);
        if (!allEntities.isEmpty()) {
            CoffeeFanEntity randomEntity = allEntities.get(rand.nextInt(allEntities.size()));
            coffeeService.consumeCoffee(randomEntity.getUsername(), getRandomCoffeeType());
        }
    }

    private CoffeeType getRandomCoffeeType() {
        return CoffeeType.values()[rand.nextInt(CoffeeType.values().length)];
    }

    private String createRandomUsername() {
        long id = repository.getNextId();
        return "user_" + id;
    }
}

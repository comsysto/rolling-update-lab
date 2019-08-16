package de.comsystoreply.rollingupdate.controller;

import de.comsystoreply.rollingupdate.data.entity.CoffeeFanEntity;
import de.comsystoreply.rollingupdate.data.entity.CoffeeType;
import de.comsystoreply.rollingupdate.service.CoffeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/fan")
public class FanController {

    private final CoffeeService coffeeService;

    public FanController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    @ResponseBody
    public Collection<CoffeeFanEntity> getFans(@RequestParam(value = "cupsDrunk") int cupsDrunk) {
        return coffeeService.getFansWithConsumedCoffee(cupsDrunk);
    }

    //    just to simplify interaction via browser
    @GetMapping
    @RequestMapping("/{username}/{coffeeType}")
    public void consumeCoffee(
            @PathVariable(value = "username") String username,
            @PathVariable(value = "coffeeType") CoffeeType coffeeType) {
        coffeeService.consumeCoffee(username, coffeeType);
    }
}

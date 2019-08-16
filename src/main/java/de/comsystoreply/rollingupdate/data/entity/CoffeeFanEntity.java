package de.comsystoreply.rollingupdate.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coffee_fans")
public class CoffeeFanEntity {
    public static final String APP_VERSION = "v1";

    @Id
    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoffeeType favouriteCoffee;

    @Column(nullable = false)
    private int cupsDrunk;


    @Column(nullable = false)
    private String appVersion = APP_VERSION;


}

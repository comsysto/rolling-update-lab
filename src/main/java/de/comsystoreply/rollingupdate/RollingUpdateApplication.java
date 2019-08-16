package de.comsystoreply.rollingupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RollingUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollingUpdateApplication.class, args);
	}

}

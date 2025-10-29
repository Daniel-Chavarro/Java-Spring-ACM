package org.apis.pokeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokeApiApplication {

    /**
     * Application entry point that starts the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    static void main(String[] args) {
        SpringApplication.run(PokeApiApplication.class, args);
    }

}
package org.apis.pokeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends RuntimeException {
    /**
     * Constructs a PokemonNotFoundException with the specified detail message.
     *
     * @param message the detail message describing the missing Pokémon
     */
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
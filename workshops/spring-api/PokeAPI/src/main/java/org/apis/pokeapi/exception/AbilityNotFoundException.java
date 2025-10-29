package org.apis.pokeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbilityNotFoundException extends RuntimeException {
    /**
     * Creates a new AbilityNotFoundException with the specified detail message.
     *
     * @param message the detail message describing the missing ability
     */
    public AbilityNotFoundException(String message) {
        super(message);
    }
}
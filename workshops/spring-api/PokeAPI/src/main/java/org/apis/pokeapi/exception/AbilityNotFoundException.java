package org.apis.pokeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbilityNotFoundException extends RuntimeException {
    public AbilityNotFoundException(String message) {
        super(message);
    }
}

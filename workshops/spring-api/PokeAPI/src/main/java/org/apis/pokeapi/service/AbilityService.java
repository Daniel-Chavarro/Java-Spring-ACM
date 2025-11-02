package org.apis.pokeapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apis.pokeapi.exception.AbilityNotFoundException;
import org.apis.pokeapi.exception.PokemonNotFoundException;
import org.apis.pokeapi.model.Ability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class AbilityService {

    /**
     * WebClient instance for making HTTP requests to the PokeAPI.
     */
    private final WebClient webClient;

    /**
     * Constructor for AbilityService.
     *
     * @param webClient The WebClient instance to be used for API calls.
     */
    @Autowired
    public AbilityService(WebClient webClient) {
        this.webClient = webClient;
    }


    /**
     * Fetches an Ability by its ID from the PokeAPI.
     *
     * @param id The ID of the Ability to fetch.
     * @return A Mono emitting the Ability object.
     */
    public Mono<Ability> getAbilityById(int id) {
        return webClient.get()
                .uri("/ability/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.error(new AbilityNotFoundException("Ability not found with ID: " + id))
                )
                .bodyToMono(JsonNode.class)
                .map(json -> new Ability(
                        json.get("id").asInt(),
                        json.get("name").asText(),
                        getShortEffect(json)
                ));
    }

    /**
     * Fetches an Ability by its name from the PokeAPI.
     *
     * @param name The name of the Ability to fetch.
     * @return A Mono emitting the Ability object.
     */
    public Mono<Ability> getAbilityByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ability name cannot be null, empty, or whitespace-only"
            ));
        }

        String normalizedName = name.trim().toLowerCase();

        return webClient.get()
                .uri("/ability/{name}", normalizedName)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.error(new AbilityNotFoundException("Ability not found with name: " + normalizedName))
                )
                .bodyToMono(JsonNode.class)
                .map(json -> new Ability(
                    json.get("id").asInt(),
                    json.get("name").asText(),
                    getShortEffect(json)
                ));
    }

    /**
     * Extracts the short effect description from the ability JSON node.
     *
     * @param node The JSON node representing the ability.
     * @return The short effect description in English, or null if not found.
     */
    private String getShortEffect(JsonNode node) {
        JsonNode entries = node.path("effect_entries");
        if (entries.isMissingNode() || !entries.isArray()) {
            return null;
        }

        return StreamSupport.stream(entries.spliterator(), false)
                .filter(entry -> "en".equals(entry.path("language").path("name").asText(null)))
                .map(entry -> entry.path("short_effect").asText(null))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}

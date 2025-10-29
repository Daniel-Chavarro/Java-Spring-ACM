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
     * Create an AbilityService backed by the provided WebClient.
     */
    @Autowired
    public AbilityService(WebClient webClient) {
        this.webClient = webClient;
    }


    /**
     * Retrieves an ability by its numeric ID from the PokeAPI.
     *
     * @return the Ability corresponding to the provided ID
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
     * Retrieves the Ability with the specified name from the PokeAPI.
     *
     * If the API responds with HTTP 404, the reactive stream will error with AbilityNotFoundException.
     *
     * @return the Ability for the specified name
     */
    public Mono<Ability> getAbilityByName(String name) {
        return webClient.get()
                .uri("/ability/{name}", name)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.error(new AbilityNotFoundException("Ability not found with name: " + name))
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
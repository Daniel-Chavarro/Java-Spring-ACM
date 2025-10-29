package org.apis.pokeapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apis.pokeapi.exception.PokemonNotFoundException;
import org.apis.pokeapi.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PokemonService {

    /**
     * WebClient instance for making HTTP requests to the PokeAPI.
     */
    private final WebClient webClient;

    /**
     * AbilityService instance for fetching abilities.
     */
    private final AbilityService abilityService;

    /**
     * Constructor for PokemonService.
     *
     * @param webClient The WebClient instance to be used for API calls.
     */
    @Autowired
    public PokemonService(WebClient webClient, AbilityService abilityService) {
        this.abilityService = abilityService;
        this.webClient = webClient;
    }

    /**
     * Fetches a Pokemon by its name from the PokeAPI.
     *
     * @param name The name of the Pokemon to fetch.
     * @return A Mono emitting the Pokemon object.
     */
    public Mono<Pokemon> getPokemonByName(String name) {
        // Preflight validation: trim and check for blank/whitespace-only
        if (name == null || name.trim().isEmpty()) {
            return Mono.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Pokemon name cannot be null, empty, or whitespace-only"
            ));
        }

        // Normalize the name: trim and convert to lowercase
        String normalizedName = name.trim().toLowerCase();

        return webClient.get()
                .uri("/pokemon/{name}", normalizedName)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        _ -> Mono.error(new PokemonNotFoundException("Pokemon not found: " + normalizedName))
                )
                .bodyToMono(JsonNode.class)
                .flatMap(json -> {
                    int id = json.path("id").asInt();
                    String pokemonName = json.path("name").asText();
                    int weight = json.path("weight").asInt();

                    List<String> abilityNames = StreamSupport
                            .stream(json.path("abilities").spliterator(), false)
                            .map(n -> n.path("ability").path("name").asText(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

                    return Flux.fromIterable(abilityNames)
                            .flatMap(abilityService::getAbilityByName)
                            .collectList()
                            .map(abilities -> new Pokemon(id, pokemonName, weight, abilities));
                });

    }
}

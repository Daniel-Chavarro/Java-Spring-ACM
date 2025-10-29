package org.apis.pokeapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apis.pokeapi.exception.PokemonNotFoundException;
import org.apis.pokeapi.model.Ability;
import org.apis.pokeapi.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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
     * Create a PokemonService configured with the HTTP client and ability resolver.
     *
     * @param webClient the WebClient used to call the external PokeAPI
     * @param abilityService the service used to fetch Ability details by name
     */
    @Autowired
    public PokemonService(WebClient webClient, AbilityService abilityService) {
        this.abilityService = abilityService;
        this.webClient = webClient;
    }

    /**
     * Retrieves a Pokemon by name from the PokeAPI and resolves its abilities.
     *
     * @return the Pokemon with its id, name, weight, and a list of resolved Ability objects
     */
    public Mono<Pokemon> getPokemonByName(String name) {
        return webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.error(new PokemonNotFoundException("Pokemon not found: " + name))
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
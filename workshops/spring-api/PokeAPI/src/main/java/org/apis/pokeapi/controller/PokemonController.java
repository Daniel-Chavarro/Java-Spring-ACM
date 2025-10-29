package org.apis.pokeapi.controller;


import org.apis.pokeapi.model.Pokemon;
import org.apis.pokeapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
    @RequestMapping("/pokeapi/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    /**
     * Constructs a PokemonController and sets the PokemonService used to fetch Pokémon data.
     */
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * Retrieve a Pokémon by its name.
     *
     * @param name the name of the Pokémon to retrieve (provided as the "name" query parameter)
     * @return the Pokemon matching the provided name
     */
    @GetMapping
    public Mono<Pokemon> getPokemonByName(@RequestParam String name) {
        return pokemonService.getPokemonByName(name);
    }
}
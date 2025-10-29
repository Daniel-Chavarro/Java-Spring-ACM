package org.apis.pokeapi.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    /**
     * Pokemon unique identifier.
     */
    private int id;

    /**
     * Pokemon name.
     */
    private String name;

    /**
     * Pokemon weight.
     */
    private int weight;

    /**
     * List of abilities that the Pokemon possesses.
     */
    private List<Ability> abilities;

    /**
     * Constructor for Pokemon class.
     *
     * @param id        The unique identifier for the Pokemon.
     * @param name      The name of the Pokemon.
     * @param weight    The weight of the Pokemon.
     * @param abilities A list of abilities that the Pokemon possesses.
     */
    public Pokemon(int id, String name, int weight, List<Ability> abilities) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.abilities = abilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}

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
     * Create a Pokemon with the specified id, name, weight, and abilities.
     *
     * @param id        the Pokemon's unique identifier
     * @param name      the Pokemon's name
     * @param weight    the Pokemon's weight
     * @param abilities the list of abilities the Pokemon possesses
     */
    public Pokemon(int id, String name, int weight, List<Ability> abilities) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.abilities = abilities;
    }

    /**
     * Get the Pokemon's unique identifier.
     *
     * @return the Pokemon's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Pokemon's unique identifier.
     *
     * @param id the unique identifier for the Pokemon
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Pokemon's name.
     *
     * @return the Pokemon's name, or {@code null} if not set.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Pokemon's name.
     *
     * @param name the name to assign to this Pokemon
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Pokemon's weight.
     *
     * @return the weight of the Pokemon
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the Pokemon's weight.
     *
     * @param weight the Pokemon's weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the list of abilities for this Pokemon.
     *
     * @return the list of abilities, or `null` if none has been set
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Sets the Pokemon's abilities.
     *
     * @param abilities the list of abilities to assign to this Pokemon
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
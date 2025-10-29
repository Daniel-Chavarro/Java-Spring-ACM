package org.apis.pokeapi.model;

import java.io.Serializable;

public class Ability implements Serializable {
    /**
     * Ability unique identifier.
     */
    private int id;
    /**
     * Ability name.
     */
    private String name;
    /**
     * Ability effect description.
     */
    private String effect;


    /**
     * Creates an Ability with the specified identifier, name, and effect description.
     *
     * @param id     the ability's unique identifier
     * @param name   the ability's name
     * @param effect the ability's effect description
     */
    public Ability(int id, String name, String effect) {
        this.id = id;
        this.name = name;
        this.effect = effect;
    }

    /**
     * Gets the ability's unique identifier.
     *
     * @return the ability's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ability's unique identifier.
     *
     * @param id the identifier to assign to this ability
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ability's name.
     *
     * @return the ability's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ability's name.
     *
     * @param name the new name for the ability
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the effect description of the ability.
     *
     * @return the ability's effect description
     */
    public String getEffect() {
        return effect;
    }

    /**
     * Sets the ability's effect description.
     *
     * @param effect the effect description
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }
}
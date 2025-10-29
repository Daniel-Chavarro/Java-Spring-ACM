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
     * Constructor for Ability class.
     *
     * @param id     The unique identifier for the ability.
     * @param name   The name of the ability.
     * @param effect The effect description of the ability.
     */
    public Ability(int id, String name, String effect) {
        this.id = id;
        this.name = name;
        this.effect = effect;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}

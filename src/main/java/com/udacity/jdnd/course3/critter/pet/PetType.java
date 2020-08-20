package com.udacity.jdnd.course3.critter.pet;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A example list of pet type metadata that could be included on a request to create a pet.
 */
public enum PetType {

    CAT, DOG, LIZARD, BIRD, FISH, SNAKE, OTHER;

    @JsonCreator
    public static PetType fromString(String key) {
        for (PetType type : PetType.values()) {
            if (type.name().equalsIgnoreCase(key)) {
                return type;
            }
        }

        return OTHER;
    }

}

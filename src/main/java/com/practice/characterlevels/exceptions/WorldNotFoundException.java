package com.practice.characterlevels.exceptions;

public class WorldNotFoundException extends RuntimeException{

    public WorldNotFoundException(Long worldId) {
        super("The World Id of '" + worldId + "' does not exist");
    }
}

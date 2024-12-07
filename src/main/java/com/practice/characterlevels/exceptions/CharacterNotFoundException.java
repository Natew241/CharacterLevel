package com.practice.characterlevels.exceptions;

public class CharacterNotFoundException extends RuntimeException{

    public CharacterNotFoundException(Long playerId, Long worldId){
        super("The Character Belonging to '" + playerId + "' and World '" + worldId + "' does not exist");
    }
}

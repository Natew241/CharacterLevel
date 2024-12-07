package com.practice.characterlevels.exceptions;

import java.awt.*;

public class NoCharacterOnWorldException extends RuntimeException{

    public NoCharacterOnWorldException(Long playerId, Long worldId){
        super("There are no characters on world '" + worldId + "'for the player with and id of '" +  playerId);
    }
}

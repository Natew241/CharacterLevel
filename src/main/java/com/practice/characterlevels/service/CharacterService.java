package com.practice.characterlevels.service;

import com.practice.characterlevels.entitiy.Character;

import java.util.List;

public interface CharacterService{

    Character getCharacter(Long playerId, Long worldId);
    Character saveCharacter(Character character, Long playerId, Long worldId);
    Character updateCharacter(String level, Long playerId, Long worldId);
    void deleteCharacter(Long playerId, Long worldId);
    List<Character> getPlayerCharacters(Long playerId);
    List<Character> getWorldCharacters(Long worldId);
    List<Character> getAllCharacters();
}

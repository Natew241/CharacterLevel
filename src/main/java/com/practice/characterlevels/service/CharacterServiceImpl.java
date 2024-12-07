package com.practice.characterlevels.service;

import com.practice.characterlevels.entitiy.Character;
import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.exceptions.CharacterNotFoundException;
import com.practice.characterlevels.exceptions.NoCharacterOnWorldException;
import com.practice.characterlevels.repository.CharacterRepository;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.repository.WorldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {

    PlayerRepository playerRepository;
    CharacterRepository characterRepository;
    WorldRepository worldRepository;

    @Override
    public Character getCharacter(Long playerId, Long worldId){
        Optional<Character> character = characterRepository.findByPlayerIdAndWorldId(playerId, worldId);
        return unwrapCharacter(character, playerId, worldId);
    }

    @Override
    public Character saveCharacter(Character character, Long playerId, Long worldId){
        Player player = PlayerServiceImpl.unwrapPlayer(playerRepository.findById(playerId), playerId);
        World world = WorldServiceImpl.unwrapWorld(worldRepository.findById(worldId), worldId);
        character.setPlayer(player);
        character.setWorld(world);
        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(String level, Long playerId, Long worldId) {
        Optional<Character> character = characterRepository.findByPlayerIdAndWorldId(playerId, worldId);
        Character unwrappedCharacter = unwrapCharacter(character, playerId, worldId);
        Player player = PlayerServiceImpl.unwrapPlayer(playerRepository.findById(playerId), playerId);
        World world = WorldServiceImpl.unwrapWorld(worldRepository.findById(worldId), worldId);
        if (!player.getWorlds().contains(world)) throw new NoCharacterOnWorldException(playerId, worldId);
        unwrappedCharacter.setLevel(level);
        return characterRepository.save(unwrappedCharacter);
    }

    @Override
    public void deleteCharacter(Long playerId, Long worldId) {
        characterRepository.deleteByPlayerIdAndWorldId(playerId, worldId);
    }

    @Override
    public List<Character> getPlayerCharacters(Long playerId) {
        return characterRepository.findByPlayerId(playerId);
    }

    @Override
    public List<Character> getWorldCharacters(Long worldId) {
        return characterRepository.findByWorldId(worldId);
    }

    @Override
    public List<Character> getAllCharacters() {
        return (List<Character>) characterRepository.findAll();
    }


    static Character unwrapCharacter(Optional<Character> entity, Long playerId, Long worldId) {
        if (entity.isPresent()) return entity.get();
        else throw new CharacterNotFoundException(playerId, worldId);
    }

}

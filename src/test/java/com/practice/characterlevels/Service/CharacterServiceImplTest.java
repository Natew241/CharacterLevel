package com.practice.characterlevels.Service;

import com.practice.characterlevels.entitiy.Character;
import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.repository.CharacterRepository;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.repository.WorldRepository;
import com.practice.characterlevels.service.CharacterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceImplTest {

    @Mock
    CharacterRepository characterRepository;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    WorldRepository worldRepository;

    @InjectMocks
    CharacterServiceImpl characterService;

    @Test
    void saveCharacterTest(){
        Player player = new Player("Joe Shmo", "jshmo@someemail.com");
        player.setId(1L);
        World world = new World("Asgard", "NA");
        world.setId(1L);

        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        when(worldRepository.findById(world.getId())).thenReturn(Optional.of(world));

        Character expectedCharacter = new Character();
        when(characterRepository.save(expectedCharacter)).thenReturn(expectedCharacter);
        Character actual = characterService.saveCharacter(expectedCharacter, player.getId(), world.getId());
        assertThat(actual).isEqualTo(expectedCharacter);
        verify(characterRepository, times(1)).save(expectedCharacter);
    }

    @Test
    void getCharacterTest(){
        Character expected = new Character("11");
        expected.setId(1L);
        expected.setCharacterName("Ted");

        when(characterRepository.findByPlayerIdAndWorldId(1L,1L)).thenReturn(Optional.of(expected));
        assertEquals(expected, characterService.getCharacter(1L, 1L));
        verify(characterRepository, times(1)).findByPlayerIdAndWorldId(1L,1L);
    }

    @Test
    void deleteCharacterTest(){
        characterService.deleteCharacter(1L, 1L);
        verify(characterRepository, times(1)).deleteByPlayerIdAndWorldId(1L,1L);
    }

    @Test
    void getPlayerCharacterTest(){
        Player player = new Player("Joe Shmo", "jshmo@someemail.com");
        player.setId(1L);

        List<Character> characters = Arrays.asList(
                new Character("11"),
                new Character("15")
        );

        player.setCharacters(characters);

        when(characterRepository.findByPlayerId(player.getId())).thenReturn(characters);

        List<Character> actual = characterService.getPlayerCharacters(player.getId());

        assertEquals(characters, actual);
        verify(characterRepository, times(1)).findByPlayerId(1L);

    }

    @Test
    void getCharactersByWorldIdTest(){
        World world = new World("Asgard", "NA");
        world.setId(1L);

        List<Character> characters = Arrays.asList(
                new Character("11"),
                new Character("15")
        );

        world.setCharacter(characters);

        when(characterRepository.findByWorldId(world.getId())).thenReturn(characters);

        List<Character> actual =  characterService.getWorldCharacters(world.getId());

        assertEquals(characters, actual);
        verify(characterRepository, times(1)).findByWorldId(world.getId());
    }

    @Test
    void getCharactersTest(){
        List<Character> characters = Arrays.asList(
                new Character("20"),
                new Character("15")
        );

        when(characterRepository.findAll()).thenReturn(characters);

        List<Character> actual = characterService.getAllCharacters();

        assertEquals(characters, actual);
        verify(characterRepository, times(1)).findAll();
    }

    @Test
    void updateCharacterTest(){
        Player player = new Player("Joe Shmo", "jshmo@someemail.com");
        player.setId(1L);
        World world = new World("Asgard", "NA");
        world.setId(1L);
        Character character = new Character("10");

        Set<World> worlds = new HashSet<>();
        worlds.add(world);
        player.setWorlds(worlds);

        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        when(worldRepository.findById(world.getId())).thenReturn(Optional.of(world));
        when(characterRepository.findByPlayerIdAndWorldId(1L,1L)).thenReturn(Optional.of(character));



        Character actual = characterService.updateCharacter("15", 1L, 1L);

        assertThat(character).isNotEqualTo(actual);
        verify(characterRepository, times(1)).save(character);
    }

}

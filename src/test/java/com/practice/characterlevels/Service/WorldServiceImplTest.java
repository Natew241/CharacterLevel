package com.practice.characterlevels.Service;



import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.repository.WorldRepository;
import com.practice.characterlevels.service.WorldServiceImpl;
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
public class WorldServiceImplTest {

    @Mock
    WorldRepository worldRepository;

    @InjectMocks
    WorldServiceImpl worldService;

    @Test
    void saveWorldTest(){
        World expectedWorld = new World();
        when(worldRepository.save(expectedWorld)).thenReturn(expectedWorld);
        World actual = worldService.saveWorld(expectedWorld);
        assertThat(actual).isEqualTo(expectedWorld);
        verify(worldRepository, times(1)).save(expectedWorld);
    }

    @Test
    void getWorldTest(){
        World expectedWorld = new World();
        expectedWorld.setId(1L);
        expectedWorld.setWorldName("Asgard");
        expectedWorld.setLocation("NA");
        when(worldRepository.findById(expectedWorld.getId())).thenReturn(Optional.of(expectedWorld));
        World actual = worldService.getWorld(expectedWorld.getId());
        assertThat(actual).isEqualTo(expectedWorld);
        verify(worldRepository, times(1)).findById(expectedWorld.getId());
    }


    @Test
    void getWorldsTest(){
        List<World> worlds = Arrays.asList(
                new World("Asgard", "NA"),
                new World("Origimar", "SA")
        );

        when(worldRepository.findAll()).thenReturn(worlds);
        assertEquals(worlds, worldService.getWorlds());
        verify(worldRepository, times(1)).findAll();
    }

    @Test
    void deleteWorldTest(){
        World world = new World();
        world.setId(1L);
        world.setWorldName("Asgard");
        world.setLocation("NA");
        worldService.deleteWorld(world.getId());
        verify(worldRepository,times(1)).deleteById(world.getId());
    }

    @Test
    void getPlayersFromWorldTest(){
        World expectedWorld = new World();
        Player player1 = new Player();
        Player player2 = new Player();

        Set<Player> worldPlayers = new HashSet<>();

        worldPlayers.add(player1);
        worldPlayers.add(player2);

        expectedWorld.setId(1L);
        expectedWorld.setWorldName("Asgard");
        expectedWorld.setLocation("NA");
        expectedWorld.setPlayers(worldPlayers);

        when(worldRepository.findById(1L)).thenReturn(Optional.of(expectedWorld));

        World world = worldService.getWorld(expectedWorld.getId());

        verify(worldRepository, times(1)).findById(1L);
        assertEquals(worldPlayers, world.getPlayers());
    }

    @Test
    void signPlayerUpForWorldTest(){
        World world = new World();
        world.setId(1L);
        world.setWorldName("Asgard");
        world.setLocation("NA");

        Set<Player> players = new HashSet<>();

        Player player = new Player("Joe Shmo", "jshmo@someemail.com");
        player.setId(1L);

        players.add(player);

        world.setPlayers(players);

        when(worldRepository.findById(world.getId())).thenReturn(Optional.of(world));
        worldRepository.save(world);
        verify(worldRepository,times(1)).save(world);
        assertEquals(players,worldService.getPlayersFromWorld(world.getId()));
    }
}

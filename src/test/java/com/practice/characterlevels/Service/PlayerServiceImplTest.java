package com.practice.characterlevels.Service;


import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.service.PlayerServiceImpl;
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
public class PlayerServiceImplTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    @Test
    void savePlayerTest() {
        Player expectedPlayer = new Player();
        when(playerRepository.save(expectedPlayer)).thenReturn(expectedPlayer);
        Player actual = playerService.savePlayer(expectedPlayer);
        assertThat(actual).isEqualTo(expectedPlayer);
        verify(playerRepository, times(1)).save(expectedPlayer);

    }

    @Test
    void getPlayerTest(){
        Player expectedPlayer = new Player();
        expectedPlayer.setId(1L);
        expectedPlayer.setPlayerName("Joe Shmo");
        expectedPlayer.setEmail("jshmo@someemail.com");
        when(playerRepository.findById(expectedPlayer.getId())).thenReturn(Optional.of(expectedPlayer));
        Player actual = playerService.getPlayer(expectedPlayer.getId());
        assertThat(actual).isEqualTo(expectedPlayer);
        verify(playerRepository, times(1)).findById(expectedPlayer.getId());
    }

    @Test
    void getPlayersTest(){
        List<Player> excpectedPlayers = Arrays.asList(
                new Player("Joe Shmo", "jshmo@someemail.com" ),
                new Player("Rob Dirt", "rdirt@someemail.com")
        );

        when(playerRepository.findAll()).thenReturn(excpectedPlayers);

        List<Player> actual = playerService.getPlayers();

        verify(playerRepository, times(1)).findAll();
        assertThat(excpectedPlayers).isEqualTo(actual);
    }

    @Test
    void deletePlayerTest(){
        Player player = new Player();
        player.setId(1L);
        player.setPlayerName("Joe Shmo");
        player.setEmail("jshmo@someemail.com");
        playerService.deletePlayer(player.getId());
        verify(playerRepository,times(1)).deleteById(player.getId());
    }

    @Test
    void getWorldsForPlayerTest(){
        Player expectedPlayer = new Player();
        World world1 = new World();
        World world2 = new World();

        Set<World> playerWorlds = new HashSet<>();

        playerWorlds.add(world1);
        playerWorlds.add(world2);

        expectedPlayer.setId(1L);
        expectedPlayer.setPlayerName("Joe Shmo");
        expectedPlayer.setEmail("jshmo@someemail.com");
        expectedPlayer.setWorlds(playerWorlds);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(expectedPlayer));

        Player player = playerService.getPlayer(expectedPlayer.getId());

        verify(playerRepository, times(1)).findById(1L);
        assertEquals(playerWorlds, player.getWorlds());

    }
}

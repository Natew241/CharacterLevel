package com.practice.characterlevels.Service;


import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PlayerServiceImplTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;

    @Test
    void savePlayerTest(){
        Player expectedPlayer = new Player();
        when(playerRepository.save(expectedPlayer)).thenReturn(expectedPlayer);

        Player actual = playerService.savePlayer(expectedPlayer);

        assertThat(actual).isEqualTo(expectedPlayer);

    }
}

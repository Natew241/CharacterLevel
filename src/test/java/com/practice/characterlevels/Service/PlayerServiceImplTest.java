package com.practice.characterlevels.Service;


import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.service.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
}

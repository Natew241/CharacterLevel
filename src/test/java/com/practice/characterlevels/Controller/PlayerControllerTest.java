package com.practice.characterlevels.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.characterlevels.controller.PlayerController;
import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlayerService playerService;

    @Test
    public void getPlayerTest() throws Exception {
        Long playerId = 1L;
        RequestBuilder request = get("/player/{id}", playerId);
        Player expected = new Player();
        expected.setPlayerName("Nate");
        expected.setEmail("Nate@natedog.com");
        when(playerService.getPlayer(playerId)).thenReturn(expected);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        Player actual = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), Player.class);
        assertThat(actual.getPlayerName()).isEqualTo(expected.getPlayerName());
        assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        verify(playerService, times(1)).getPlayer(playerId);

    }

}

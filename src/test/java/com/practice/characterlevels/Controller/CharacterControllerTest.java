package com.practice.characterlevels.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.characterlevels.controller.CharacterController;
import com.practice.characterlevels.entitiy.Character;
import com.practice.characterlevels.service.CharacterService;
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

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CharacterService characterService;

    @Test
    public void getCharacterTest() throws Exception {
        Long playerId = 1L;
        Long worldId = 1L;
        RequestBuilder request = get("/character/player/{playerId}/world/{worldId}", playerId, worldId);
        Character expected = new Character();
        expected.setCharacterName("Ted");
        expected.setLevel("11");
        when(characterService.getCharacter(playerId, worldId)).thenReturn(expected);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        Character actual = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), Character.class);
        assertThat(actual.getCharacterName()).isEqualTo(expected.getCharacterName());
        assertThat(actual.getLevel()).isEqualTo(expected.getLevel());
        verify(characterService, times(1)).getCharacter(1L,1L);
    }
}

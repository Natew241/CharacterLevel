package com.practice.characterlevels.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.characterlevels.controller.WorldController;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.service.WorldServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorldController.class)
public class WorldControllerTest {

    @Autowired
    private MockMvc mockMvc; // Injected by Spring, not mocked

    @MockitoBean
    private WorldServiceImpl worldService; // Mocked by Spring

    @Test
    public void getWorldTest() throws Exception {
        // Arrange
        Long worldId = 1L;
        RequestBuilder request = get("/world/{id}", worldId);

        World expected = new World();
        expected.setWorldName("Asgard");
        expected.setLocation("NA");

        // Mock the service method
        when(worldService.getWorld(worldId)).thenReturn(expected);

        // Act
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        World actual = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), World.class);

        assertThat(actual.getWorldName()).isEqualTo(expected.getWorldName());
        assertThat(actual.getLocation()).isEqualTo(expected.getLocation());

        verify(worldService, times(1)).getWorld(worldId);
    }
}


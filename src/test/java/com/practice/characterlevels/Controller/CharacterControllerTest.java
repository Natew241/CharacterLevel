package com.practice.characterlevels.Controller;


import com.practice.characterlevels.controller.CharacterController;
import com.practice.characterlevels.entitiy.Character;
import com.practice.characterlevels.repository.CharacterRepository;
import com.practice.characterlevels.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;


    @Test
    public void getCharacterTest() throws Exception{

//        when(characterService.getCharacter(1,2)).thenReturn(
//                new Character());
    }
}

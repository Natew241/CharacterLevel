package com.practice.characterlevels.controller;

import com.practice.characterlevels.entitiy.Character;
import com.practice.characterlevels.service.CharacterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    CharacterService characterService;

    @GetMapping("/player/{playerId}/world/{serverId}")
    public ResponseEntity<Character> getCharacter(@PathVariable Long playerId, @PathVariable Long serverId){
        return new ResponseEntity<>(characterService.getCharacter(playerId, serverId), HttpStatus.OK);
    }

    @PostMapping("/player/{playerId}/world/{worldId}")
    public ResponseEntity<Character> saveCharacter(@Valid @RequestBody Character character, @PathVariable Long playerId, @PathVariable Long worldId) {
        return new ResponseEntity<>(characterService.saveCharacter(character, playerId, worldId), HttpStatus.CREATED);
    }


    @PutMapping("/player/{playerId}/world/{worldId}")
    public ResponseEntity<Character> updateCharacter(@RequestBody @NonNull Character character, @PathVariable Long playerId, @PathVariable Long worldId) {
        return new ResponseEntity<>(characterService.updateCharacter(character.getLevel(), playerId, worldId), HttpStatus.OK);
    }

    @DeleteMapping("/player/{playerId}/world/{worldId}")
    public ResponseEntity<HttpStatus> deleteCharacter(@PathVariable Long playerId, @PathVariable Long worldId) {
        characterService.deleteCharacter(playerId, worldId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Character>> getPlayerCharacters(@PathVariable Long playerId) {
        return new ResponseEntity<>(characterService.getPlayerCharacters(playerId), HttpStatus.OK);
    }

    @GetMapping("/world/{worldId}")
    public ResponseEntity<List<Character>> getWorldCharacters(@PathVariable Long worldId) {
        return new ResponseEntity<>(characterService.getWorldCharacters(worldId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Character>> getCharacters() {
        return new ResponseEntity<>(characterService.getAllCharacters(), HttpStatus.OK);
    }


}

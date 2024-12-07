package com.practice.characterlevels.controller;

import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id){
        return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayers(){
        return new ResponseEntity<>(playerService.getPlayers(), HttpStatus.OK);
    }

    @GetMapping("{playerId}/world")
    public ResponseEntity<Set<World>> getWorldsForPlayer(@PathVariable Long playerId){
        return new ResponseEntity<>(playerService.getWorldsForPlayer(playerId), HttpStatus.OK);
    }

}

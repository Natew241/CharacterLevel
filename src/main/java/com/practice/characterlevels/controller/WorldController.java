package com.practice.characterlevels.controller;


import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.service.WorldService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/world")
public class WorldController {

    WorldService worldService;

    @PostMapping
    public ResponseEntity<World> saveServer(@RequestBody World world){
        return new ResponseEntity<>(worldService.saveWorld(world), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<World> getWorld(@PathVariable Long id){
        return new ResponseEntity<>(worldService.getWorld(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWorld(@PathVariable Long id){
        worldService.deleteWorld(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<World>> getWorlds(){
        return new ResponseEntity<>(worldService.getWorlds(), HttpStatus.OK);
    }

    @PutMapping("/{worldId}/player/{playerId}")
    public ResponseEntity<World> signPlayerUpForWorld(@PathVariable Long worldId, @PathVariable Long playerID){
        return new ResponseEntity<>(worldService.signPlayerUpForWorld(worldId, playerID ), HttpStatus.OK);
    }

}

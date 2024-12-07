package com.practice.characterlevels.service;

import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.exceptions.WorldNotFoundException;
import com.practice.characterlevels.repository.PlayerRepository;
import com.practice.characterlevels.repository.WorldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class WorldServiceImpl implements WorldService {

    WorldRepository worldRepository;
    PlayerRepository playerRepository;

    @Override
    public World saveWorld(World world){
        return worldRepository.save(world);
    }

    @Override
    public World getWorld(Long worldId){
        Optional<World> world = worldRepository.findById(worldId);
        return unwrapWorld(world, worldId);
    }

    @Override
    public void deleteWorld(Long worldId){
        worldRepository.deleteById(worldId);
    }

    @Override
    public List<World> getWorlds(){
        return (List<World>) worldRepository.findAll();
    }

    @Override
    public Set<Player> getPlayersFromWorld(Long worldId){
        World world = getWorld(worldId);
        return world.getPlayers();
    }

    @Override
    public World signPlayerUpForWorld(Long worldId, Long playerId){
        World world = getWorld(worldId);
        Optional<Player> player = playerRepository.findById(playerId);
        Player unwrappedPlayer = PlayerServiceImpl.unwrapPlayer(player, playerId);
        world.getPlayers().add(unwrappedPlayer);
        return worldRepository.save(world);
    }

    static World unwrapWorld(Optional<World> entity, Long worldId){
        if (entity.isPresent()) return entity.get();
        else throw new WorldNotFoundException(worldId);
    }
}

package com.practice.characterlevels.service;

import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;
import com.practice.characterlevels.exceptions.PlayerNotFoundException;
import com.practice.characterlevels.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService{

    PlayerRepository playerRepository;

    @Override
    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(Long playerId){
        Optional<Player> player = playerRepository.findById(playerId);
        return unwrapPlayer(player, playerId);
    }

    @Override
    public void deletePlayer(Long playerId){
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> getPlayers(){
        return (List<Player>)playerRepository.findAll();
    }

    @Override
    public Set<World> getWorldsForPlayer(Long playerId){
        Player player = getPlayer(playerId);
        return player.getWorlds();
    }

    static Player unwrapPlayer(Optional<Player> entity, Long playerId){
        if (entity.isPresent()) return entity.get();
        else throw new PlayerNotFoundException(playerId);
    }
}

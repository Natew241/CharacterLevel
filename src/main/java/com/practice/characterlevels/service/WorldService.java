package com.practice.characterlevels.service;


import com.practice.characterlevels.entitiy.Player;
import com.practice.characterlevels.entitiy.World;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WorldService {
    World saveWorld(World world);
    World getWorld(Long id);
    void deleteWorld(Long id);
    World signPlayerUpForWorld(Long worldId, Long playerId);
    List<World> getWorlds();
    Set<Player> getPlayersFromWorld(Long worldId);
}

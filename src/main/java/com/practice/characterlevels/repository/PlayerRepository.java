package com.practice.characterlevels.repository;

import com.practice.characterlevels.entitiy.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}

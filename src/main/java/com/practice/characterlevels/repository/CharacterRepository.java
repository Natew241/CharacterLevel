package com.practice.characterlevels.repository;

import com.practice.characterlevels.entitiy.Character;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {
    Optional<Character> findByPlayerIdAndWorldId(Long playerId, Long worldId);
    List<Character> findByPlayerId(Long playerId);
    List<Character> findByWorldId(Long worldId);
    @Transactional
    void deleteByPlayerIdAndWorldId(Long playerId, Long worldId);
}

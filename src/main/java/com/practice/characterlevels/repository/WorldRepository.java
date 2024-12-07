package com.practice.characterlevels.repository;

import com.practice.characterlevels.entitiy.World;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldRepository extends CrudRepository<World, Long> {

}

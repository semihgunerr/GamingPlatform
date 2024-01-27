package org.semihg.gamingplatform.repository;

import org.semihg.gamingplatform.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByName(String name);

    Set<Game> findByGenre(String genre);
}

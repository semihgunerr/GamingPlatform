package org.semihg.gamingplatform.repository;

import org.semihg.gamingplatform.entity.Game;
import org.semihg.gamingplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    Optional<User> findById(Long id);

    Set<Game> findGamesById(Long id);
}
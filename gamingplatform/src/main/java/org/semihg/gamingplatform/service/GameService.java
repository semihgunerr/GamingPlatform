package org.semihg.gamingplatform.service;

import org.semihg.gamingplatform.dto.AddGameRequest;
import org.semihg.gamingplatform.dto.GameResponse;
import org.semihg.gamingplatform.entity.Game;
import org.semihg.gamingplatform.entity.User;
import org.semihg.gamingplatform.repository.GameRepository;
import org.semihg.gamingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }
    public GameResponse addGame(AddGameRequest req){
    try{
        if(gameRepository.findByName(req.getName())!=null){

            return new GameResponse("already exists",null);
        }
    }
    catch (Exception e){
        return new GameResponse(e.getMessage(),null);
    }

    Game newGame= new Game();
    newGame.setName(req.getName());
    newGame.setGenre(req.getGenre());
    newGame.setPlatform(req.getPlatform());
    newGame.setReleaseDate(req.getReleaseDate());

    try{
        return new GameResponse("success", Collections.singleton(gameRepository.save(newGame)));
    }

    catch (Exception e) {
        return new GameResponse(e.getMessage(),null);
        }
    }
    public GameResponse deleteGame(Long id) {
        try{
            Optional<Game> g = gameRepository.findById(id);
            if(g.isEmpty()){

                return new GameResponse("game not found",null);
            }
            gameRepository.deleteById(id);
            return new GameResponse("success",Collections.singleton(g.get()) );
        }
        catch (Exception e){
            return new GameResponse(e.getMessage(),null);
        }

    }

    public GameResponse getGame(Long id) {

        try{
            Optional<Game> game = gameRepository.findById(id);
            if(game.isEmpty()){

                return new GameResponse("not found",null);
            }

                return new GameResponse("success",Collections.singleton(game.get()));
        }
        catch (Exception e){
            return new GameResponse(e.getMessage(),null);
        }

    }

    public GameResponse getGenre(String genre){

        try{
            Set<Game> gameSet = gameRepository.findByGenre(genre);
            return new GameResponse("success",gameSet);
        }
        catch (Exception e){
            return new GameResponse("not found",null);

        }
    }

    public GameResponse buyGame(Long id, Long user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        try {
            if (optionalUser.isPresent()) {
                Optional<Game> optionalGame = gameRepository.findById(id);

                if (optionalGame.isPresent()) {
                    User user = optionalUser.get();
                    user.getGames().add(optionalGame.get());

                    userRepository.save(user);
                    return new GameResponse("success",user.getGames());

                }
                return new GameResponse("not found",null);

            }
            return new GameResponse("not found",null);
        }
        catch (Exception e){
            return new GameResponse("not found",null);

        }
    }
}

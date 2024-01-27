package org.semihg.gamingplatform.controller;

import org.semihg.gamingplatform.dto.AddGameRequest;
import org.semihg.gamingplatform.dto.GameResponse;
import org.semihg.gamingplatform.dto.UserResponse;
import org.semihg.gamingplatform.entity.Game;
import org.semihg.gamingplatform.repository.UserRepository;
import org.semihg.gamingplatform.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Game>> getGameList() {
        List<Game> gameList = gameService.getAllGames();
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }


    @GetMapping
    ResponseEntity<GameResponse> getGame(@RequestParam Long id) {
        GameResponse res = gameService.getGame(id);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/genre")
    ResponseEntity<GameResponse> getGenre(@RequestParam String genre) {
        GameResponse res = gameService.getGenre(genre);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/buy")
    ResponseEntity<GameResponse> buyGame(@RequestParam Long id, @RequestParam Long userId) {
        GameResponse res = gameService.buyGame(id, userId);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/remove")
    ResponseEntity<GameResponse> deleteGame(@RequestParam Long id) {

        GameResponse res = gameService.deleteGame(id);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    ResponseEntity<GameResponse> addGame(@RequestBody AddGameRequest req) {

        GameResponse res = gameService.addGame(req);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);


    }

}

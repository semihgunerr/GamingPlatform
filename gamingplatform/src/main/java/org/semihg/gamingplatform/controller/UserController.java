package org.semihg.gamingplatform.controller;

import org.semihg.gamingplatform.dto.*;
import org.semihg.gamingplatform.entity.Game;
import org.semihg.gamingplatform.entity.User;
import org.semihg.gamingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest req) {

        UserResponse res = userService.createUser(req);
        if (res.getUser() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/library")
    ResponseEntity<GameResponse> getGameLibrary(@RequestParam Long id) {

        GameResponse res = userService.getLibrary(id);
        if (res.getGame() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);


    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestParam Long id) {

        UserResponse res = userService.getProfile(id);
        if (res.getUser() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<UserResponse> removeProfile(@RequestParam Long id) {
        UserResponse res = userService.removeProfile(id);
        if (res.getUser() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest req) {

        UserResponse res = userService.login(req);
        if (res.getUser() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }

    @PatchMapping("/edit")
    public ResponseEntity<UserResponse> editProfile(@RequestBody EditProfileRequest req) {


        UserResponse res = userService.editProfile(req.getUsername(), req.getPassword(), req.getId());
        if (res.getUser() != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);


    }

}

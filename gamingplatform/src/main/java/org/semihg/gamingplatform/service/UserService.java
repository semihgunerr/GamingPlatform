package org.semihg.gamingplatform.service;

import org.semihg.gamingplatform.dto.CreateUserRequest;
import org.semihg.gamingplatform.dto.GameResponse;
import org.semihg.gamingplatform.dto.LoginRequest;
import org.semihg.gamingplatform.dto.UserResponse;
import org.semihg.gamingplatform.entity.Game;
import org.semihg.gamingplatform.entity.User;
import org.semihg.gamingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private Map<User, Integer> loginAttempts = new HashMap<>();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest req) {
        try {
            if (userRepository.findByEmail(req.getEmail()) != null) {
                return new UserResponse("already exists", null);
            }
        } catch (Exception e) {
            return new UserResponse(e.getMessage(), null);
        }

        // Create a new user
        User newUser = new User();
        newUser.setUsername(req.getUsername());
        newUser.setEmail(req.getEmail());
        newUser.setPassword(req.getPassword());
        try {
            return new UserResponse("success", userRepository.save(newUser));
        } catch (Exception e) {
            return new UserResponse(e.getMessage(), null);
        }
    }

    public UserResponse getProfile(Long id) {
        try {
            Optional<User> u = userRepository.findById(id);
            if (u.isEmpty()) {
                return new UserResponse("user not found", null);
            }

            return new UserResponse("success", u.get());
        } catch (Exception e) {

            return new UserResponse(e.getMessage(), null);
        }

    }


    public UserResponse login(LoginRequest req) {
        try {
            User u = userRepository.findByEmail(req.getEmail());
            if (u == null) {
                return new UserResponse("user not found", null);
            }
            if (u.getPassword().equals(req.getPassword())) {
                return new UserResponse("success", u);
            }

            int attempts = loginAttempts.getOrDefault(u, 0) + 1;
            loginAttempts.put(u, attempts);

            // Check if the user has exceeded the maximum number of attempts
            if (attempts >= MAX_LOGIN_ATTEMPTS) {
                // Handle the case where the user exceeded the maximum attempts (lock the account, send notification, etc.)
                return new UserResponse("User " + u.getUsername() + " has been locked due to too many login attempts.", null);
            }

            return new UserResponse("invalid password", null);

        } catch (Exception e) {

            return new UserResponse(e.getMessage(), null);
        }


    }

    public UserResponse removeProfile(Long id) {

        try {
            Optional<User> u = userRepository.findById(id);
            if (u.isEmpty()) {
                return new UserResponse("user not found", null);
            }
            userRepository.deleteById(id);
            return new UserResponse("success", u.get());
        } catch (Exception e) {

            return new UserResponse(e.getMessage(), null);
        }

    }

    public GameResponse getLibrary(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return new GameResponse("success", user.get().getGames());

        } catch (Exception e) {
            return new GameResponse("success", null);
        }
    }

    public UserResponse editProfile(String username, String password, Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return new UserResponse("user not found", null);

            }
            if (user.get().getPassword().equals(password) && user.get().getUsername().equals(username))
                return new UserResponse("credentials are same", null);

            User u = user.get();
            u.setUsername(username);
            u.setPassword(password);
            userRepository.save(u);
            return new UserResponse("success", u);
        } catch (Exception e) {
            return new UserResponse(e.getMessage(), null);
        }
    }
}

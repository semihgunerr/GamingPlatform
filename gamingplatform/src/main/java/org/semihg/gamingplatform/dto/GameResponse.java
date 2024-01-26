package org.semihg.gamingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semihg.gamingplatform.entity.Game;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    private String message;
    private Set<Game> game;
}

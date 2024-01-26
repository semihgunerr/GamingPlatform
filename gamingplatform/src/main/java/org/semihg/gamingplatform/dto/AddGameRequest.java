package org.semihg.gamingplatform.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddGameRequest {
    private String genre;
    private String name;
    private LocalDate releaseDate;
    private String platform;

}

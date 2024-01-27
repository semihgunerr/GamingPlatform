package org.semihg.gamingplatform.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddGameRequest {

    private String name;
    private String genre;
    private LocalDate releaseDate;
    private String platform;

}

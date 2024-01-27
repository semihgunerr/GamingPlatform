package org.semihg.gamingplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User users;
}

package org.semihg.gamingplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_game",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "game_id")
    )
    private Set<Game> games = new HashSet<>();
}

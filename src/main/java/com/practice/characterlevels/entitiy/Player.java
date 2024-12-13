package com.practice.characterlevels.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name must be filled in")
    @NonNull
    @Column(name = "player_name")
    private String playerName;

    @NotBlank(message = "Email must be filled in")
    @NonNull
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Character> characters;

    @Getter
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "world_player",
            joinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "world_id", referencedColumnName = "id")
    )
    private Set<World> worlds = new HashSet<>();

}

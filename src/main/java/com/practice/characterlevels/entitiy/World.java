package com.practice.characterlevels.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "World")
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotBlank(message = "World name cannot be blank")
    @Column(name = "World_name")
    private String worldName;

    @NonNull
    @NotBlank(message = "Location cannot be blank")
    @Column(name = "location")
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "world", cascade = CascadeType.ALL)
    private List<Character> character;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "world_player",
            joinColumns = @JoinColumn(name = "world_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id")
    )
    private Set<Player> players;

}

package com.practice.characterlevels.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Character name can not be blank")
    @Column(name = "character_name", nullable = false)
    private String characterName;

    @NonNull
    @Column(name = "level", nullable = false)
    private String level;

    @ManyToOne(optional = false)
    @JoinColumn(name = "world_id", referencedColumnName = "id")
    private World world;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

}

package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(unique = true)
    private String reference;

    @Column
    private int minAge;

    @Column
    private String description;

    @Column
    private int duration;

    @NonNull
    @Column(nullable = false)
    private Float dailyPrice;

    @OneToMany(mappedBy = "game")
    private List<GameCopy> copies;

    @ManyToMany(targetEntity = Genre.class)
    private List<Genre> genres;

}

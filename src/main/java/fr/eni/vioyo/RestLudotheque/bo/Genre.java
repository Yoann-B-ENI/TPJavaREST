package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(unique=true)
    private String label;

    @ManyToMany(targetEntity=Game.class, mappedBy="genres")
    private List<Game> games;
}

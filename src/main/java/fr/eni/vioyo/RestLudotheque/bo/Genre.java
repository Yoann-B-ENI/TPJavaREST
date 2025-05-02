package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Genre extends EntityBaseClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(unique=true, nullable=false)
    @NonNull
    private String label;

    @ManyToMany(targetEntity=Game.class, mappedBy="genres")
    private List<Game> games;
}

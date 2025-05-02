package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class GameCopy extends EntityBaseClass{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private int barCode;

    @Column
    private boolean rentable;


    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id",name = "game_id")
    private Game game;

}

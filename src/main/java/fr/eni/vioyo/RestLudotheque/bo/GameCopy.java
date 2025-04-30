package fr.eni.vioyo.RestLudotheque.bo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GameCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private int barCode;

    @Column
    private boolean rentable;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id",name = "game_id" )
    private Game game;

}

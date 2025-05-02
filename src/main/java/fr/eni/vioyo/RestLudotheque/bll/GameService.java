package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Game;
import fr.eni.vioyo.RestLudotheque.bo.Genre;
import fr.eni.vioyo.RestLudotheque.dto.RentableGameDTO;

import java.util.List;

public interface GameService {

    Game add(Game game);

    Game add(Game game, List<Genre> genres);

    List<RentableGameDTO> findRentableGames();
}

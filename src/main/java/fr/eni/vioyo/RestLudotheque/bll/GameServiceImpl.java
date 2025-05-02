package fr.eni.vioyo.RestLudotheque.bll;

import fr.eni.vioyo.RestLudotheque.bo.Game;
import fr.eni.vioyo.RestLudotheque.bo.GameCopy;
import fr.eni.vioyo.RestLudotheque.bo.Genre;
import fr.eni.vioyo.RestLudotheque.dal.GameRepository;
import fr.eni.vioyo.RestLudotheque.dto.RentableGameDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game add(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game add(Game game, List<Genre> genres) {
        game.setGenres(genres);
        return this.add(game);
    }

    @Override
    public List<RentableGameDTO> findRentableGames() {
        List<Game> allGames = gameRepository.findAll();
        List<RentableGameDTO> rentableGames = new ArrayList<>();

        for (Game game : allGames) {

            int rentableCopies = (int) game.getCopies().stream()
                    .filter(GameCopy::isRentable)
                    .count();

            if (rentableCopies > 0) {
                RentableGameDTO dto = new RentableGameDTO();
                dto.setId(game.getId());
                dto.setTitle(game.getTitle());
                dto.setReference(game.getReference());
                dto.setRentableCopies(rentableCopies);
                dto.setGenres(game.getGenres().stream().map(Genre::getLabel).toList());
                rentableGames.add(dto);
            }
        }
        if (rentableGames.isEmpty()) {
            throw new RuntimeException("There are no rentable games");
        }

        return rentableGames;
    }

}

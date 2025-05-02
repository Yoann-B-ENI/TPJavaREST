package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bll.GameServiceImpl;
import fr.eni.vioyo.RestLudotheque.bo.Game;
import fr.eni.vioyo.RestLudotheque.bo.GameCopy;
import fr.eni.vioyo.RestLudotheque.bo.Genre;
import fr.eni.vioyo.RestLudotheque.dto.RentableGameDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameServiceImpl gameService;

    @MockitoBean
    private GameRepository gameRepository;

    @Test
    @DisplayName("add game without genres")
    public void shouldAddGameSuccessfully() {
        //Arrange
        Game game = new Game("Catan", "REF123", 5.0f);

        org.mockito.Mockito.doAnswer((invocation) -> {
            Game g = invocation.getArgument(0);
            g.setId(999);
            return g;
        }).when(gameRepository).save(game);

        //Act
        Game savedGame = gameService.add(game);

        //Assert
        assertThat(game.getId()).isNotNull();
        assertThat(game.getId()).isEqualTo(999);
    }


    @Test
    @DisplayName("add game with genres")
    public void shouldAddGameWithGenresSuccessfully() {
        //Arrange
        Game game = new Game("Catan", "REF123", 5.0f);
        Genre genre1 = new Genre("Stratégies");
        Genre genre2 = new Genre("Famille");
        List<Genre> genres = new ArrayList<Genre>();
        genres.add(genre1);
        genres.add(genre2);

        org.mockito.Mockito.doAnswer((invocation) -> {
            Game g = invocation.getArgument(0);
            g.setId(999);
            g.setGenres(genres);
            return g;
        }).when(gameRepository).save(game);

        //Act
        Game savedGame = gameService.add(game, genres);

        //Assert
        assertThat(game.getId()).isNotNull();
        assertThat(game.getId()).isEqualTo(999);
        assertEquals(savedGame.getGenres(), genres);
    }

    @Test
    @DisplayName("Throw Exception when no games are Rentable")
    void shouldReturnRentableGamesSuccessfully() {
        Game game1 = new Game("Catan", "REF001", 3.0f);
        Game game2 = new Game("Risk", "REF002", 2.0f);

        game1.setGenres(List.of(new Genre("Stratégie")));
        game2.setGenres(List.of(new Genre("Guerre")));

        GameCopy c1 = new GameCopy(); c1.setRentable(true);
        GameCopy c2 = new GameCopy(); c2.setRentable(false);

        GameCopy c3 = new GameCopy(); c3.setRentable(true);
        GameCopy c4 = new GameCopy(); c4.setRentable(true);

        game1.setCopies(List.of(c1, c2));
        game2.setCopies(List.of(c3, c4));

        when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

        List<RentableGameDTO> rentableGames = gameService.findRentableGames();

        assertEquals(2, rentableGames.size());
        assertEquals("Catan", rentableGames.get(0).getTitle());
        assertEquals("Risk", rentableGames.get(1).getTitle());
        assertEquals(2, rentableGames.get(1).getRentableCopies());
        assertEquals(1, rentableGames.get(0).getRentableCopies());
    }

    @Test
    @DisplayName("get all rentable games")
    void shouldReturnAvailableGamesWithFilters() {
        Game game1 = new Game("Catan", "REF001", 3.0f);
        Game game2 = new Game("Risk", "REF002", 2.0f);

        game1.setGenres(List.of(new Genre("Stratégie")));
        game2.setGenres(List.of(new Genre("Guerre")));

        GameCopy c1 = new GameCopy(); c1.setRentable(true);
        GameCopy c2 = new GameCopy(); c2.setRentable(false);

        GameCopy c3 = new GameCopy(); c3.setRentable(true);
        GameCopy c4 = new GameCopy(); c4.setRentable(true);

        game1.setCopies(List.of(c1, c2));
        game2.setCopies(List.of(c3, c4));

        when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

        List<RentableGameDTO> rentableGames = gameService.findRentableGames();

        assertEquals(2, rentableGames.size());
        assertEquals("Catan", rentableGames.get(0).getTitle());
        assertEquals("Risk", rentableGames.get(1).getTitle());
        assertEquals(2, rentableGames.get(1).getRentableCopies());
        assertEquals(1, rentableGames.get(0).getRentableCopies());
    }



}

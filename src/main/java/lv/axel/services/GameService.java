package lv.axel.services;

import lv.axel.domain.Game;
import lv.axel.domain.PlayerPlace;
import lv.axel.domain.Player;
import lv.axel.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerService playerService;

    @Transactional
    public Game addGame(Game game) {
        for (PlayerPlace playerPlace : game.getPlayersPlaces()) {
            Player player = playerService.findPlayerById(playerPlace.getPlayer().getId());
            playerPlace.setPlayer(player);
            playerPlace.setGame(game);
        }
        return gameRepository.save(game);
    }

    @Transactional
    public void updateGame(Game game) {
        Game gameFromDB = gameRepository.getOne(game.getId());
        gameFromDB.getPlayersPlaces().clear();
        gameFromDB.getPlayersPlaces().addAll(game.getPlayersPlaces());

        for (PlayerPlace playerPlace : game.getPlayersPlaces()) {
            Player player = playerService.findPlayerById(playerPlace.getPlayer().getId());
            playerPlace.setPlayer(player);
            playerPlace.setGame(gameFromDB);
        }
        gameRepository.save(game);
    }

    public void deleteGameById(Long id) {
        gameRepository.delete(id);
    }

    public List<Game> getAllGames(Integer year) {
        if (year != null){
            LocalDateTime startDate = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(year, Month.DECEMBER, 31, 23, 59);
            Date currentYearFirstDate = Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant());
            Date currentYearLastDate = Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant());

            return gameRepository.findByDateBetween(currentYearFirstDate, currentYearLastDate);
        } else {
            return gameRepository.findAll();
        }
    }

    public Game getGameById(Long id) {
        return gameRepository.getOne(id);
    }
}

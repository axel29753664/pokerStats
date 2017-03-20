package lv.axel.services;

import lv.axel.domain.Game;
import lv.axel.domain.PlayerPlace;
import lv.axel.domain.Player;
import lv.axel.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerService playerService;

    @Transactional
    public void addGame(Game game) {
        for (PlayerPlace playerPlace : game.getPlayersPlaces()) {
            Player player = playerService.findPlayerById(playerPlace.getPlayer().getId());
            playerPlace.setPlayer(player);
            playerPlace.setGame(game);
        }
        gameRepository.save(game);
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

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}

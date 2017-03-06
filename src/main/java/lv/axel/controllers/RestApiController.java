package lv.axel.controllers;

import lv.axel.domain.Game;
import lv.axel.domain.Player;
//import lv.axel.playerRepository.PlaceRepository;
import lv.axel.model.GameDTO;
import lv.axel.model.PlayerDTO;
import lv.axel.model.StatsDTO;
import lv.axel.repository.GameRepository;
import lv.axel.repository.PlayerRepository;
import lv.axel.services.*;
import lv.axel.services.dtoConverters.GameDTOConverter;
import lv.axel.services.dtoConverters.PlayerDTOConverter;
import lv.axel.services.dtoConverters.StatsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestApiController {

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping("getStats")
    StatsDTO getStats() {

        List<Player> players = playerService.getAllPlayers();
        List<Game> games = gameService.getAllGames();

        return StatsDTOConverter.convertEntity(players, games);
    }

    @RequestMapping("updateGame")
    ResponseEntity updateGame(@RequestBody GameDTO gameDTO) {

        Game game = GameDTOConverter.convertToEntity(gameDTO);
        gameService.updateGame(game);

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping("deleteGames")
    ResponseEntity deleteGames(@RequestBody List<GameDTO> ids) {
        System.out.println(ids);
//        gameService.deleteGames(games);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("addGame")
    ResponseEntity addGame(@RequestBody GameDTO gameDTO) {

        Game game = GameDTOConverter.convertToEntity(gameDTO);
        gameService.addGame(game);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("addPlayer")
    ResponseEntity savePlayer(@RequestBody PlayerDTO playerDTO) {

        Player player = PlayerDTOConverter.convertToEntity(playerDTO);
        playerService.addPlayer(player);

        return new ResponseEntity(HttpStatus.OK);
    }
}

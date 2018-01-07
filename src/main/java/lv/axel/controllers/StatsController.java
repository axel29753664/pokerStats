package lv.axel.controllers;

import lv.axel.domain.Game;
import lv.axel.domain.Player;
//import lv.axel.playerRepository.PlaceRepository;
import lv.axel.model.ErrorModel;
import lv.axel.model.GameDTO;
import lv.axel.model.PlayerDTO;
import lv.axel.model.StatsDTO;
import lv.axel.services.*;
import lv.axel.services.dtoConverters.GameDTOConverter;
import lv.axel.services.dtoConverters.PlayerDTOConverter;
import lv.axel.services.dtoConverters.StatsDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class StatsController {

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "getStats", method = RequestMethod.GET)
    public ResponseEntity<StatsDTO> getStats(@RequestParam(value = "year", required = false) Integer year) {

        List<Player> players = playerService.getAllPlayers();
        List<Game> games = gameService.getAllGames(year);
        StatsDTO statsDTO = StatsDTOConverter.convertToDTO(players, games);

        return new ResponseEntity<>(statsDTO, HttpStatus.OK);
    }

}

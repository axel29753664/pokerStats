package lv.axel.controllers;

import lv.axel.domain.Game;
import lv.axel.model.ErrorModel;
import lv.axel.model.GameDTO;
import lv.axel.services.GameService;
import lv.axel.services.dtoConverters.GameDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api")
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping("updateGame")
    ResponseEntity updateGame(@RequestBody GameDTO gameDTO) {

        Game game = GameDTOConverter.convertToEntity(gameDTO);
        gameService.updateGame(game);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("deleteGames")
    ResponseEntity<?> deleteGames(@RequestBody List<Long> ids) {
        System.out.println(ids);
        try {
            for (Long id : ids) {
                gameService.deleteGameById(id);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("addGame")
    ResponseEntity addGame(@RequestBody GameDTO gameDTO) {

        Game game = GameDTOConverter.convertToEntity(gameDTO);
        gameService.addGame(game);

        return new ResponseEntity(HttpStatus.OK);
    }
}

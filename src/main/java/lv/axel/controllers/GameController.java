package lv.axel.controllers;

import lv.axel.domain.Game;
import lv.axel.domain.History;
import lv.axel.model.ErrorModel;
import lv.axel.model.GameDTO;
import lv.axel.model.PlayerPlaceDTO;
import lv.axel.services.GameService;
import lv.axel.services.HistoryService;
import lv.axel.services.dtoConverters.GameDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("auth/admin/updateGame")
    public ResponseEntity updateGame(@RequestBody GameDTO gameDTO) {

        try {
            Game game = GameDTOConverter.convertToEntity(gameDTO);
            Game gameFromDB = gameService.getGameById(game.getId());
            String action = gameFromDB + " to " + game;
            gameService.updateGame(game);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            History history = new History(new Date(), auth.getName(), "UPDATE", action);
            historyService.saveAction(history);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("auth/admin/deleteGames")
    public ResponseEntity<?> deleteGames(@RequestBody List<Long> ids) {
        try {
            for (Long id : ids) {
                Game game = gameService.getGameById(id);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                History history = new History(new Date(), auth.getName(), "DELETE", game.toString());
                historyService.saveAction(history);
                gameService.deleteGameById(id);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("auth/addGame")
    public ResponseEntity addGame(@RequestBody GameDTO gameDTO) {

        Game game = GameDTOConverter.convertToEntity(gameDTO);
        Game gameFromDB = gameService.addGame(game);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        History history = new History(new Date(), auth.getName(), "CREATE", gameFromDB.toString());
        historyService.saveAction(history);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package lv.axel.controllers;

import lv.axel.domain.History;
import lv.axel.domain.Player;
import lv.axel.model.ErrorModel;
import lv.axel.model.PlayerDTO;
import lv.axel.services.HistoryService;
import lv.axel.services.PlayerService;
import lv.axel.services.dtoConverters.PlayerDTOConverter;
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
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("auth/admin/deletePlayers")
    public ResponseEntity<?> deletePlayer(@RequestBody List<Long> ids) {

        try {
            for (Long id : ids) {
                Player player = playerService.findPlayerById(id);
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                History history = new History(new Date(), auth.getName(), "DELETE", player.toString());
                historyService.saveAction(history);
                playerService.deletePlayerById(id);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("auth/addPlayer")
    public ResponseEntity savePlayer(@RequestBody PlayerDTO playerDTO) {

        Player player = PlayerDTOConverter.convertToEntity(playerDTO);
        Player playerFromDB = playerService.addPlayer(player);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        History history = new History(new Date(), auth.getName(), "CREATE", playerFromDB.toString());
        historyService.saveAction(history);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package lv.axel.controllers;

import lv.axel.domain.Player;
import lv.axel.model.ErrorModel;
import lv.axel.model.PlayerDTO;
import lv.axel.services.PlayerService;
import lv.axel.services.dtoConverters.PlayerDTOConverter;
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
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping("deletePlayers")
    public ResponseEntity<?> deletePlayer(@RequestBody List<Long> ids) {

        try {
            for (Long id : ids) {
                playerService.deletePlayerById(id);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("addPlayer")
    ResponseEntity savePlayer(@RequestBody PlayerDTO playerDTO) {

        Player player = PlayerDTOConverter.convertToEntity(playerDTO);
        playerService.addPlayer(player);

        return new ResponseEntity(HttpStatus.OK);
    }
}

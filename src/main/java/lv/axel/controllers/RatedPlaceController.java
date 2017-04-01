package lv.axel.controllers;

import lv.axel.domain.History;
import lv.axel.domain.RatedPlaces;
import lv.axel.model.ErrorModel;
import lv.axel.model.RatedPlacesDTO;
import lv.axel.services.HistoryService;
import lv.axel.services.RatedPlacesService;
import lv.axel.services.dtoConverters.RatedPlaceDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@RequestMapping("api")
public class RatedPlaceController {
    @Autowired
    private RatedPlacesService ratedPlacesService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "getAllRatedPlaces", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRatedPlaces() {

        List<RatedPlaces> ratedPlacesList = ratedPlacesService.getAllRatedPlaces();
        List<RatedPlacesDTO> ratedPlacesDTOList = new ArrayList<>();

        for (RatedPlaces ratedPlaces : ratedPlacesList) {
            RatedPlacesDTO ratedPlacesDTO = RatedPlaceDTOConverter.convertToDTO(ratedPlaces);
            ratedPlacesDTOList.add(ratedPlacesDTO);
        }
        return new ResponseEntity<>(ratedPlacesDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "auth/admin/updateRatedPlaces", method = RequestMethod.POST)
    public ResponseEntity<?> updateRatedPlaces(@RequestBody List<RatedPlacesDTO> ratedPlacesDTOList) {
        List<RatedPlaces> ratedPlacesFromDB = ratedPlacesService.getAllRatedPlaces();
        String action = "Rated Places: " + ratedPlacesFromDB + " to " + ratedPlacesDTOList;
        try {
            for (RatedPlacesDTO ratedPlacesDTO : ratedPlacesDTOList) {
                RatedPlaces ratedPlaces = RatedPlaceDTOConverter.convertToEntity(ratedPlacesDTO);
                ratedPlacesService.save(ratedPlaces);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        History history = new History(new Date(), auth.getName(), "UPDATE", action);
        historyService.saveAction(history);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

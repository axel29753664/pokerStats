package lv.axel.controllers;

import lv.axel.domain.RatedPlaces;
import lv.axel.model.RatedPlacesDTO;
import lv.axel.services.RatedPlacesService;
import lv.axel.services.dtoConverters.RatedPlaceDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RatedPlaceController {
    @Autowired
    private RatedPlacesService ratedPlacesService;

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

}

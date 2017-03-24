package lv.axel.services.dtoConverters;

import lv.axel.domain.RatedPlaces;
import lv.axel.model.RatedPlacesDTO;

public class RatedPlaceDTOConverter {
    public static RatedPlacesDTO convertToDTO(RatedPlaces ratedPlaces){
        return new RatedPlacesDTO(ratedPlaces.getPlayersQuantity(), ratedPlaces.getRatedPlacesQuantity());
    }
}

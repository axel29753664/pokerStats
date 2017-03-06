package lv.axel.services.dtoConverters;

import lv.axel.domain.Player;
import lv.axel.domain.PlayerPlace;
import lv.axel.model.PlayerDTO;
import lv.axel.model.PlayerPlaceDTO;

public class PlayerPlaceDTOConverter {

    public static PlayerPlaceDTO convertToDTO(PlayerPlace playerPlace) {
        PlayerPlaceDTO playerPlaceDTO = new PlayerPlaceDTO();
        PlayerDTO playerDTO = PlayerDTOConverter.convertToDTO(playerPlace.getPlayer());
        playerPlaceDTO.setPlayer(playerDTO);
        playerPlaceDTO.setPlace(playerPlace.getPlace());
        return playerPlaceDTO;
    }

    public static PlayerPlace convertToEntity(PlayerPlaceDTO playerPlaceDTO) {
        PlayerPlace playerPlace = new PlayerPlace();
        Player player = PlayerDTOConverter.convertToEntity(playerPlaceDTO.getPlayer());
        playerPlace.setPlayer(player);
        playerPlace.setPlace(playerPlaceDTO.getPlace());
        //set game
        return playerPlace;
    }
}

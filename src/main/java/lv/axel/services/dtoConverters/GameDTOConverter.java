package lv.axel.services.dtoConverters;

import lv.axel.domain.Game;
import lv.axel.domain.PlayerPlace;
import lv.axel.model.GameDTO;
import lv.axel.model.PlayerPlaceDTO;


import java.util.*;

public class GameDTOConverter {

    public static Game convertToEntity(GameDTO gameDTO) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setDate(gameDTO.getDate());
        Set<PlayerPlace> playersPlaces = new HashSet<>();
        for (PlayerPlaceDTO playerPlaceDTO : gameDTO.getPlayersPlaces()) {
            PlayerPlace playerPlace = PlayerPlaceDTOConverter.convertToEntity(playerPlaceDTO);
            playerPlace.setGame(game);
            playersPlaces.add(playerPlace);
        }
        game.setPlayersPlaces(playersPlaces);
        return game;
    }

    public static GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setDate(game.getDate());
        Set<PlayerPlaceDTO> playersPlacesDTO = new TreeSet<>();
        for (PlayerPlace playerPlace : game.getPlayersPlaces()) {
            playersPlacesDTO.add(PlayerPlaceDTOConverter.convertToDTO(playerPlace));
        }

        gameDTO.setPlayersPlaces(playersPlacesDTO);
        return gameDTO;
    }
}

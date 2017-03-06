package lv.axel.services.dtoConverters;

import lv.axel.domain.Game;
import lv.axel.domain.Player;
import lv.axel.domain.PlayerPlace;
import lv.axel.model.GameDTO;
import lv.axel.model.PlayerDTO;
import lv.axel.model.PlayerPlaceDTO;
import lv.axel.model.StatsDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatsDTOConverter {
    public static StatsDTO convertEntity(List<Player> players, List<Game> games) {

        StatsDTO statsDTO = new StatsDTO();
        List<GameDTO> gameDTOList = new ArrayList<>();
        for (Game game : games) {
            List<PlayerPlaceDTO> playerPlacesDTO = new ArrayList<>();
            for (PlayerPlace playerPlace : game.getPlayersPlaces()) {
                playerPlacesDTO.add(PlayerPlaceDTOConverter.convertToDTO(playerPlace));
            }
            gameDTOList.add(new GameDTO(game.getId(),game.getDate(), playerPlacesDTO));
        }

        List<PlayerDTO> playersDTO = new ArrayList<>();
        for (Player player : players) {
            playersDTO.add(PlayerDTOConverter.convertToDTO(player));
        }
        statsDTO.setGames(gameDTOList);
        statsDTO.setAllPlayers(playersDTO);
        return statsDTO;
    }
}

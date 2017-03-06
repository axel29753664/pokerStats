package lv.axel.services.dtoConverters;

import lv.axel.domain.Player;
import lv.axel.model.PlayerDTO;
import lv.axel.model.PlayerPlaceDTO;

public class PlayerDTOConverter {
    public static PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        return playerDTO;
    }

    public static Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        return player;
    }
}

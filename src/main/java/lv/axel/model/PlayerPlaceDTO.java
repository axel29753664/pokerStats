package lv.axel.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlayerPlaceDTO implements Comparable<PlayerPlaceDTO> {
    private PlayerDTO player;
    private int place;

    @Override
    public int compareTo(PlayerPlaceDTO playerPlaceDTO) {
        return player.compareTo(playerPlaceDTO.getPlayer());
    }
}

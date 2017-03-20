package lv.axel.model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO implements Comparable<GameDTO> {
    private Long id;
    private Date date;
    private Set<PlayerPlaceDTO> playersPlaces;

    @Override
    public int compareTo(GameDTO gameDTO) {
        return -date.compareTo(gameDTO.getDate());
    }
}

package lv.axel.model;

import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO implements Comparable<GameDTO> {
    private Long id;
    private Date date;
    private Set<PlayerPlaceDTO> playersPlaces;

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Game: ")
                .append("ID: ")
                .append(id)
                .append(", date: ")
                .append(dateFormat.format(date))
                .append(" [");
        Set<PlayerPlaceDTO> playerPlaceDTOSet = new TreeSet<>(playersPlaces);
        for (PlayerPlaceDTO playerPlaceDTO : playerPlaceDTOSet) {
            stringBuilder.append(playerPlaceDTO.getPlayer().getName())
                    .append(":")
                    .append(playerPlaceDTO.getPlace())
                    .append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(GameDTO gameDTO) {
        return -date.compareTo(gameDTO.getDate());
    }
}

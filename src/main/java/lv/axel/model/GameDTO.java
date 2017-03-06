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
public class GameDTO {
    private Long id;
    private Date date;
    private List<PlayerPlaceDTO> playersPlaces;
}

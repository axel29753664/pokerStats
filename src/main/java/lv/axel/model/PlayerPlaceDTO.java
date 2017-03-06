package lv.axel.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlayerPlaceDTO {
    private PlayerDTO player;
    private int place;
}

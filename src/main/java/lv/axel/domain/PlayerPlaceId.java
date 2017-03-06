package lv.axel.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PlayerPlaceId implements Serializable {
    private Long game;
    private Long player;
}

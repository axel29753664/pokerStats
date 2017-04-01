package lv.axel.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "place")
@Entity
@IdClass(PlayerPlaceId.class)
@Table(name = "player_places")
public class PlayerPlace  implements Comparable<PlayerPlace>{

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "place")
    private int place;

    @Override
    public int compareTo(PlayerPlace playerPlace) {
        return player.compareTo(playerPlace.getPlayer());
    }
}

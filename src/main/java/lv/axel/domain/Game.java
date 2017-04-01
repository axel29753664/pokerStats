package lv.axel.domain;

import lombok.*;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@RequiredArgsConstructor
//@ToString(exclude = {"playersPlaces"})
@EqualsAndHashCode(exclude = {"playersPlaces"})
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlayerPlace> playersPlaces;

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
        Set<PlayerPlace> playerPlaceSet = new TreeSet<>(playersPlaces);
        for (PlayerPlace playerPlace : playerPlaceSet) {
            stringBuilder.append(playerPlace.getPlayer().getName())
                    .append(":")
                    .append(playerPlace.getPlace())
                    .append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

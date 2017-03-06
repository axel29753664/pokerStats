package lv.axel.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"playerPlaces"})
@EqualsAndHashCode(exclude = {"playerPlaces"})
@Entity
@Table(name = "players")
public class Player  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlayerPlace> playerPlaces;

    public Player(String name) {
        this.name = name;
    }

}

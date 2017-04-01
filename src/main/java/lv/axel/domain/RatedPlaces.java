package lv.axel.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@EqualsAndHashCode
@Entity
@Table(name = "rated_places")
public class RatedPlaces {
    @Id
    @Column(name = "players_quantity")
    private int playersQuantity;
    @Column(name = "rated_places_quantity")
    private int ratedPlacesQuantity;

    @Override
    public String toString() {
        return playersQuantity +":" + ratedPlacesQuantity;
    }
}

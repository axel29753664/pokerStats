package lv.axel.model;

import lombok.*;

@Getter
@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RatedPlacesDTO {
    private int playersQuantity;
    private int ratedPlacesQuantity;

    @Override
    public String toString() {
        return playersQuantity + ":" + ratedPlacesQuantity;
    }
}

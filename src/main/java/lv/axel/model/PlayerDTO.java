package lv.axel.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO implements Comparable<PlayerDTO> {
    private Long id;
    private String name;

    @Override
    public int compareTo(PlayerDTO playerDTO) {
        return name.compareTo(playerDTO.getName());
    }
}

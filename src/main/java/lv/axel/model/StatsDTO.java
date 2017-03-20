package lv.axel.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StatsDTO {
    private Set<GameDTO> games;
    private Set<PlayerDTO> allPlayers;
}

package lv.axel.repository;

import lv.axel.domain.Player;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByName(String name);
}

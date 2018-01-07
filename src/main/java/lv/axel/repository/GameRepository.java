package lv.axel.repository;

import lv.axel.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select gm from Game gm where gm.date between ?1 and ?2 order by gm.date desc")
    List<Game> findByDateBetween(Date startDate, Date endDate);
}

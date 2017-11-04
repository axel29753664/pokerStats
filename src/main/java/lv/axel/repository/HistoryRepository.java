package lv.axel.repository;

import lv.axel.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByOrderByDateDesc();

    @Query("select b from History b " + "where b.date between ?1 and ?2 order by b.date desc")
    List<History> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate);
}

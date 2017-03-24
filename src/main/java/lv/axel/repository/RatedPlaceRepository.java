package lv.axel.repository;

import lv.axel.domain.RatedPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatedPlaceRepository extends JpaRepository<RatedPlaces, Integer> {
}

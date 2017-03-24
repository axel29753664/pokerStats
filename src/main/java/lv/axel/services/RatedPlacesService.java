package lv.axel.services;

import lv.axel.domain.RatedPlaces;
import lv.axel.repository.RatedPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatedPlacesService {
    @Autowired
    private RatedPlaceRepository ratedPlaceRepository;

    public List<RatedPlaces> getAllRatedPlaces() {
        return ratedPlaceRepository.findAll();
    }
}

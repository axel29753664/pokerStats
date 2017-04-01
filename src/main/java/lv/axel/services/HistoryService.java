package lv.axel.services;

import lv.axel.domain.History;
import lv.axel.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public History saveAction(History history) {
        return historyRepository.save(history);
    }

    public List<History> getAllHistory() {
        return historyRepository.findAllByOrderByDateDesc();
    }

    public List<History> getHistoryByDate(Date startDate, Date endDate) {
        return historyRepository.findByDateBetweenOrderByDateDesc(startDate, endDate);
    }
}

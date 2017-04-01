package lv.axel.controllers;

import lombok.Getter;
import lombok.Setter;
import lv.axel.domain.History;
import lv.axel.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "auth/getHistory")
    public ResponseEntity getHistory(@RequestBody Period period) {
        List<History> histories = historyService.getHistoryByDate(period.getStartDate(), period.getEndDate());
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}

@Getter
@Setter
class Period {
    private Date startDate;
    private Date endDate;

}

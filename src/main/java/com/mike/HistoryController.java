package com.mike;

import com.mike.DTOs.History;
import com.mike.DTOs.Record;
import com.mike.repo.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    @Autowired
    private RecordRepository repository;

    @RequestMapping(value = "/history/{name}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    History getHistoryFor(@PathVariable String name) {
        List<Record> allRecords = repository.getScoresByPlayer(name, Sort.by("score"));
        if (allRecords.isEmpty()) {
            return new History();
        }

        Record highScore = allRecords.get(0);
        Record lowScore = allRecords.get(allRecords.size() - 1);
        double avg = allRecords.stream().mapToInt(r -> r.getScore()).average().orElse(0);
        return new History(allRecords, highScore.getScore(), lowScore.getScore(), highScore.getScoreDate(), lowScore.getScoreDate(), avg);
    }
}

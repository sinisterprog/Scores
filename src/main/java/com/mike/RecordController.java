package com.mike;

import com.mike.DTOs.Record;
import com.mike.DTOs.Request;
import com.mike.repo.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordRepository repository;

    // Find
    @GetMapping("/records")
    List<Record> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/records")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Record newRecord(@RequestBody Record newRecord) {
        return repository.save(newRecord);
    }

    // Find by ID
    @GetMapping("/records/{id}")
    int findOne(@PathVariable Long id) {
        return repository.getScoreById(id).getScore();
    }

    // Find by name
    @GetMapping("/scores/{name}")
    List<Record> findByName(@PathVariable String name) {
        return repository.getScoresByPlayer(name);
    }

    // Save or update
    @PutMapping("/records/{id}")
    Record saveOrUpdate(@RequestBody Record newRecord, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setPlayer(newRecord.getPlayer());
                    x.setScore(newRecord.getScore());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newRecord.setId(id);
                    return repository.save(newRecord);
                });
    }

    @DeleteMapping("/records/{id}")
    void deleteRecord(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @RequestMapping(value = "/getRecords", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Record> getRecords(@RequestBody Request request) {
        return repository.findAll((Specification<Record>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("player"));
            request.getNames().forEach(inClause::value);
            predicates.add(inClause);
            if (request.getFromDateTime() != null) {
                Timestamp startDateTime = Timestamp.valueOf(request.getFromDateTime());
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("scoreDate"), startDateTime));
            }
            if (request.getToDateTime() != null) {
                Timestamp startDateTime = Timestamp.valueOf(request.getToDateTime());
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("scoreDate"), startDateTime));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}

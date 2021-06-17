package com.mike;

import com.mike.DTOs.Record;
import com.mike.DTOs.RecordXfer;
import com.mike.repo.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CreateController {

    @Autowired
    private RecordRepository repository;

    @RequestMapping(value = "/createRecord", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Record createRecord(
            @RequestBody RecordXfer request) throws IOException {
        Record r = new Record(request.getName(), request.getScore(), request.getDateTime());
        repository.save(r);
        return r;
    }

}
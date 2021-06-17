package com.mike;

import com.mike.DTOs.Record;
import com.mike.repo.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= StartScoresApplication.class)
public class PersistenceTests {
    @Autowired
    private RecordRepository repository;

    @Test
    public void testCreate() {
        int currentSize = repository.findAll().size();
        Record r = new Record();
        repository.save(r);
        assertTrue (r.getId() > 0);
        assertNotNull(repository.findById(r.getId()));
        assertEquals(repository.findAll().size(), currentSize+1);
    }

    @Test
    public void testDelete() {
        Record r = new Record();
        repository.save(r);
        assertTrue (r.getId() > 0);
        int currentSize = repository.findAll().size();
        repository.deleteById(r.getId());
        assertEquals(repository.findAll().size(), currentSize-1);
    }
    @Test
    public void restRead() {
        Record r = new Record();
        r.setScore(50);
        repository.save(r);
        assertTrue (r.getId() > 0);
        Record shouldExist = repository.getOne(r.getId());
        assertNotNull(shouldExist);
        Record score= repository.getScoreById(shouldExist.getId());
        assertEquals(50, score.getScore());
    }
}

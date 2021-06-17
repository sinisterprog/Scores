package com.mike.repo;

import com.mike.DTOs.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> , JpaSpecificationExecutor {
    Record getScoreById(Long id);
    List<Record> getScoresByPlayer(String player, Pageable pageable);
    List<Record> getScoresByPlayer(String player, Sort sort);


}

package com.capstone.backend.repository;

import com.capstone.backend.model.JobHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    
    List<JobHistory> findByJobCode(String jobCode);

    @Query("SELECT j FROM JobHistory j ORDER BY j.startTime DESC")
    List<JobHistory> findRecentHistory(Pageable pageable);

    default List<JobHistory> findRecentHistory() {
        return findRecentHistory(PageRequest.of(0,100));
    }
}

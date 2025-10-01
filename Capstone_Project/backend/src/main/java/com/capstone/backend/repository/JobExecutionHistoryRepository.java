package com.capstone.backend.repository;

import com.capstone.backend.model.JobExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExecutionHistoryRepository extends JpaRepository<JobExecutionHistory, Long> {
    
}

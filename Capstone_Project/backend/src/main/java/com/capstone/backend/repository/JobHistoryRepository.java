package com.capstone.backend.repository;

import com.capstone.backend.model.JobHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    List<JobHistory> findByJobCode(String jobCode);
}

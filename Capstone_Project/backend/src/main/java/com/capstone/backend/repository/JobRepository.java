package com.capstone.backend.repository;

import com.capstone.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
    
public interface JobRepository extends JpaRepository<Job, Long> {
    
    List<Job> findByActiveIgnoreCase(String active);

    Job findByJobCode(String jobCode);
}

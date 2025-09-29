package com.capstone.backend.repository;

import com.capstone.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
    
public interface JobRepository extends JpaRepository<Job, Long> {
}

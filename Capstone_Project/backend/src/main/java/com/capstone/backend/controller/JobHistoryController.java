package com.capstone.backend.controller;

import com.capstone.backend.dto.JobHistoryDTO;
import com.capstone.backend.model.JobHistory;
import com.capstone.backend.repository.JobHistoryRepository;
import com.capstone.backend.repository.JobRepository;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
public class JobHistoryController {
    
    private final JobHistoryRepository repo;
    private final JobRepository jobRepo;

    public JobHistoryController(JobHistoryRepository repo, JobRepository jobRepo) {
        this.repo = repo;
        this.jobRepo = jobRepo;
    }

    @GetMapping
    public List<JobHistoryDTO> getAllHistory () {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private JobHistoryDTO toDto(JobHistory history) {
        Integer jobType = jobRepo.findByJobCode(history.getJobCode()).getJobType();
        return new JobHistoryDTO(
                history.getId(),
                history.getJobCode(),
                jobType,
                history.getStartTime(),
                history.getEndTime(),
                history.getStatus(),
                history.getMessage()
        );
    }
}

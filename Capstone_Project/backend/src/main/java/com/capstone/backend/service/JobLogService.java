package com.capstone.backend.service;

import com.capstone.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobLogService {

    private final JobRepository jobRepository;

    public JobLogService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void markStart(Long jobId) {
        jobRepository.findById(jobId).ifPresent(job -> {
            job.setLastTriggeredDate(LocalDateTime.now());
            job.setLastResult("P");
            job.setTriggerStatus("P");
            jobRepository.save(job).getId();
        });
    }

    public void markSuccess(Long jobId) {
        jobRepository.findById(jobId).ifPresent(job -> {
            job.setLastResult("S");
            job.setTriggerStatus("S");
            jobRepository.save(job);
        });
    }

    public void markError(Long jobId) {
        jobRepository.findById(jobId).ifPresent(job -> {
            job.setLastResult("E");
            job.setTriggerStatus("E");
            jobRepository.save(job);
        });
    }
}

package com.capstone.backend.controller;

import com.capstone.backend.model.Job;
import com.capstone.backend.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repo;

    public JobController(JobRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return repo.findAll();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return repo.save(job);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        Job job = repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));

        job.setJobName(jobDetails.getJobName());
        job.setActive(jobDetails.getActive());
        job.setLastTriggeredDate(jobDetails.getLastTriggeredDate());
        job.setTriggerStatus(jobDetails.getTriggerStatus());
        job.setNextTriggerDate(jobDetails.getNextTriggerDate());
        job.setJobCode(jobDetails.getJobCode());
        job.setLastResult(jobDetails.getLastResult());
        job.setSchedulePeriod(jobDetails.getSchedulePeriod());
        job.setStartDate(jobDetails.getStartDate());
        job.setEndDate(jobDetails.getEndDate());
        job.setRunTime(jobDetails.getRunTime());
        job.setCreatedBy(jobDetails.getCreatedBy());
        job.setUpdatedBy(jobDetails.getUpdatedBy());
        job.setDateCreated(jobDetails.getDateCreated());
        job.setDateUpdated(jobDetails.getDateUpdated());
        job.setJobType(jobDetails.getJobType());

        return repo.save(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        Job job = repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        repo.delete(job);
    }
}

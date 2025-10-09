package com.capstone.backend.controller;

import com.capstone.backend.dto.JobDTO;
import com.capstone.backend.dto.JobUpdateDTO;
import com.capstone.backend.model.Job;
import com.capstone.backend.repository.JobRepository;
import com.capstone.backend.service.JobExecutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.quartz.SchedulerException;
import org.quartz.CronExpression;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repo;
    private final JobExecutorService jobExecutorService;

    public JobController(JobRepository repo, JobExecutorService jobExecutorService) {
        this.repo = repo;
        this.jobExecutorService = jobExecutorService;
    }

    @GetMapping
    public List<JobDTO> getAllJobs() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return repo.save(job);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        return repo.findById(id)
                .map(job -> ResponseEntity.ok(toDto(job)))
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobUpdateDTO jobUpdateDTO) {
        Job job = repo.findById(id).orElse(null);
        if (job ==null) {
            return ResponseEntity.notFound().build();
        }
      
        job.setSchedulePeriod(jobUpdateDTO.getSchedulePeriod());
        job.setStartDate(jobUpdateDTO.getStartDate());
        job.setEndDate(jobUpdateDTO.getEndDate());
        job.setRunTime(jobUpdateDTO.getRunTime());

        String cron = buildCronExpression(jobUpdateDTO);
        job.setCronExpression(cron);
        job.setDateUpdated(LocalDateTime.now());
        job.setUpdatedBy("system"); // Placeholder, replace with actual user info

        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date nextValidTime = cronExpression.getNextValidTimeAfter(new Date());
            if (nextValidTime != null) {
                job.setNextTriggerDate(nextValidTime.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Invalid cron expression: " + e.getMessage()
            ));
        }

        repo.save(job);

        try {
            jobExecutorService.rescheduleJob(job);
        } catch (SchedulerException e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Failed to reschedule job",
                    "details", e.getMessage()
            ));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Job " + job.getJobCode() + " updated successfully",
                "jobCode", job.getJobCode(),
                "cronExpression", job.getCronExpression(),
                "dateUpdated", job.getDateUpdated()
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        Job job = repo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        repo.delete(job);
    }

    @PostMapping("/{id}/trigger")
    public ResponseEntity<?> triggerJob(@PathVariable Long id) {
        try {
            jobExecutorService.runJobNow(id);
            return ResponseEntity.ok(Map.of(
                "message", "Job triggered successfully",
                "jobId", id
            ));
        } catch (SchedulerException e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "error", "Failed to trigger job",
                "details", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "error", e.getMessage()
            ));
        }
    }


    // Mapper
    private JobDTO toDto(Job job){
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setJobCode(job.getJobCode());
        dto.setJobName(job.getJobName());
        dto.setJobType(job.getJobType());
        dto.setActive(job.getActive());
        dto.setStatus(job.getTriggerStatus());
        dto.setLastTriggeredDate(job.getLastTriggeredDate());
        dto.setNextTriggerDate(job.getNextTriggerDate());
        dto.setStartDate(job.getStartDate());
        dto.setEndDate(job.getEndDate());
        dto.setLastResult(job.getLastResult());
        dto.setSchedulePeriod(job.getSchedulePeriod());
        dto.setCronExpression(job.getCronExpression());
        return dto;
    }

    private String buildCronExpression(JobUpdateDTO dto) {
        if (dto.getRunTime() == null || !dto.getRunTime().matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid runTime format. Expected HH:mm");
        }

        String[] timeParts = dto.getRunTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid time values in runTime.");
        }

        switch (dto.getSchedulePeriod().toUpperCase()) {
            case "DAILY":
                return String.format("0 %d %d * * ?", minute, hour);

            case "WEEKLY":
                if (dto.getDayOfWeek() == null) {
                    throw new IllegalArgumentException("Day must be specified for WEEKLY schedulePeriod.");
                }
                return String.format("0 %d %d ? * %s", minute, hour, dto.getDayOfWeek().toUpperCase());

            case "MONTHLY":
                if (dto.getDayOfMonth() == null || dto.getDayOfMonth() < 1 || dto.getDayOfMonth() > 31) {
                    throw new IllegalArgumentException("Valid date (1-31) must be specified for MONTHLY schedulePeriod.");
                }
                return String.format("0 %d %d %d * ?", minute, hour, dto.getDayOfMonth());

            default:
                throw new IllegalArgumentException(String.format("Invalid schedulePeriod: %s. Expected DAILY, WEEKLY, or MONTHLY.", dto.getSchedulePeriod()));
        }
    }
}

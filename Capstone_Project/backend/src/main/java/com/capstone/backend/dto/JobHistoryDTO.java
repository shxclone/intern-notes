package com.capstone.backend.dto;

import java.time.LocalDateTime;

public class JobHistoryDTO {
    private Long id;
    private String jobId;
    private Integer jobType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String message;

    public JobHistoryDTO(Long id, String jobId, Integer jobType, LocalDateTime startTime, LocalDateTime endTime, String status, String message) {
        this.id = id;
        this.jobId = jobId;
        this.jobType = jobType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

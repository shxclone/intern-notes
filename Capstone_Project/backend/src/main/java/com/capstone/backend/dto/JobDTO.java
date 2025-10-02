package com.capstone.backend.dto;

import java.time.LocalDateTime;

public class JobDTO {
    private Long id;
    private String jobCode;
    private String jobName;
    private Integer jobType;
    private String active;
    private String status;
    private LocalDateTime lastTriggeredDate;
    private LocalDateTime nextTriggerDate;
    private String lastResult;
    private String cronExpression;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastTriggeredDate() {
        return lastTriggeredDate;
    }

    public void setLastTriggeredDate(LocalDateTime lastTriggeredDate) {
        this.lastTriggeredDate = lastTriggeredDate;
    }

    public LocalDateTime getNextTriggerDate() {
        return nextTriggerDate;
    }

    public void setNextTriggerDate(LocalDateTime nextTriggerDate) {
        this.nextTriggerDate = nextTriggerDate;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}

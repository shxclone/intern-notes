package com.capstone.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID (Primary Key)

    @Column(name = "job_name", length = 100, nullable = false)
    private String jobName;

    @Column(name = "active", length = 1)
    private String active; // Active (Y/N)

    @Column(name = "last_triggered_date")
    private LocalDateTime lastTriggeredDate;

    @Column(name = "trigger_status", length = 10)
    private String triggerStatus; // Trigger Status (e.g., Success, Error)

    @Column(name = "next_trigger_date")
    private LocalDateTime nextTriggerDate;

    @Column(name = "job_code", length = 200)
    private String jobCode;

    @Column(name = "last_result", length = 500)
    private String lastResult;

    @Column(name = "schedule_period", length = 50)
    private String schedulePeriod; // Schedule Period (e.g., Daily, Weekly)

    @Column(name = "cron_expression", length = 100)
    private String cronExpression;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "run_time", length = 10)
    private String runTime;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "job_type")
    private Integer jobType;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public LocalDateTime getLastTriggeredDate() {
        return lastTriggeredDate;
    }

    public void setLastTriggeredDate(LocalDateTime lastTriggeredDate) {
        this.lastTriggeredDate = lastTriggeredDate;
    }

    public String getTriggerStatus() {
        return triggerStatus;
    }

    public void setTriggerStatus(String triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    public LocalDateTime getNextTriggerDate() {
        return nextTriggerDate;
    }

    public void setNextTriggerDate(LocalDateTime nextTriggerDate) {
        this.nextTriggerDate = nextTriggerDate;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public String getSchedulePeriod() {
        return schedulePeriod;
    }

    public void setSchedulePeriod(String schedulePeriod) {
        this.schedulePeriod = schedulePeriod;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }
}

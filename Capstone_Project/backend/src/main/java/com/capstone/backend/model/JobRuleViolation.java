package com.capstone.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_rule_violations")
public class JobRuleViolation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_code", nullable = false)
    private String jobCode;

    @Column(name = "rule_code", nullable = false, length = 50)
    private String ruleCode;

    @Column(name = "first_detected", nullable = false)
    private LocalDateTime firstDetected;

    @Column(name = "last_detected", nullable = false)
    private LocalDateTime lastDetected;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // ACTIVE or ACKNOWLEDGED

    @Column(name = "severity", nullable = false, length = 20)
    private String severity; // LOW, MEDIUM, HIGH

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

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

    public String getRuleCode() { 
        return ruleCode; 
    }

    public void setRuleCode(String ruleCode) { 
        this.ruleCode = ruleCode; 
    }

    public LocalDateTime getFirstDetected() { 
        return firstDetected; 
    }

    public void setFirstDetected(LocalDateTime firstDetected) { 
        this.firstDetected = firstDetected; 
    }

    public LocalDateTime getLastDetected() { 
        return lastDetected; 
    }

    public void setLastDetected(LocalDateTime lastDetected) { 
        this.lastDetected = lastDetected; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getSeverity() { 
        return severity; 
    }

    public void setSeverity(String severity) { 
        this.severity = severity; 
    }

    public String getMessage() { 
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }

    public String getUpdatedBy() { 
        return updatedBy; 
    }

    public void setUpdatedBy(String updatedBy) { 
        this.updatedBy = updatedBy; 
    }
}

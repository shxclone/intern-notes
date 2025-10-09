package com.capstone.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rule_evaluation_log")
public class RuleEvaluationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_code", nullable = false)
    private String jobCode;

    @Column(name = "rule_code", nullable = false, length = 50)
    private String ruleCode;

    @Column(name = "passed", nullable = false, length = 1)
    private String passed; // 'Y' or 'N'

    @Column(name = "evaluated_at", nullable = false)
    private LocalDateTime evaluatedAt;

    @Column(name = "message", length = 500)
    private String message;

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

    public String getPassed() { 
        return passed; 
    }

    public void setPassed(String passed) { 
        this.passed = passed; 
    }

    public LocalDateTime getEvaluatedAt() { 
        return evaluatedAt; 
    }

    public void setEvaluatedAt(LocalDateTime evaluatedAt) { 
        this.evaluatedAt = evaluatedAt; 
    }

    public String getMessage() { 
        return message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }
}

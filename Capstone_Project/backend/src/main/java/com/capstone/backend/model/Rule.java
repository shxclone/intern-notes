package com.capstone.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_code", nullable = false, unique = true, length = 50)
    private String ruleCode;

    @Column(name = "rule_name", nullable = false, length = 255)
    private String ruleName;

    @Column(name = "expression", nullable = false, length = 1000)
    private String expression;

    @Column(name = "severity", nullable = false, length = 20)
    private String severity;

    @Column(name = "active", nullable = false, length = 1)
    private String active; // 'Y' or 'N'

    @Column(name = "message_template", length = 500)
    private String messageTemplate;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    // Getters and Setters 
    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getRuleCode() { 
        return ruleCode; 
    }

    public void setRuleCode(String ruleCode) { 
        this.ruleCode = ruleCode; 
    }

    public String getRuleName() { 
        return ruleName; 
    }

    public void setRuleName(String ruleName) { 
        this.ruleName = ruleName; 
    }

    public String getExpression() { 
        return expression; 
    }

    public void setExpression(String expression) { 
        this.expression = expression; 
    }

    public String getSeverity() { 
        return severity; 
    }

    public void setSeverity(String severity) { 
        this.severity = severity; 
    }

    public String getActive() { 
        return active; 
    }

    public void setActive(String active) { 
        this.active = active; 
    }

    public String getMessageTemplate() { 
        return messageTemplate; 
    }

    public void setMessageTemplate(String messageTemplate) { 
        this.messageTemplate = messageTemplate; 
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
}

package com.capstone.backend.service;

import com.capstone.backend.model.Job;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobVariableExtractor {

    public Map<String, Object> extract(Job job) {
        Map<String, Object> vars = new HashMap<>();

        vars.put("active", job.getActive());
        vars.put("cron_expression", job.getCronExpression());
        vars.put("last_triggered_date", job.getLastTriggeredDate());

        if (job.getLastTriggeredDate() != null) {
            long daysSinceLastRun =
                    Duration.between(job.getLastTriggeredDate(), LocalDateTime.now()).toDays();
            vars.put("days_since_last_run", daysSinceLastRun);
        } else {
            vars.put("days_since_last_run", Long.MAX_VALUE);
        }

        return vars;
    }
}

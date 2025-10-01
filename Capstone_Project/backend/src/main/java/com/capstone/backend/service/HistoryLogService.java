package com.capstone.backend.service;

import com.capstone.backend.model.JobExecutionHistory;
import com.capstone.backend.repository.JobExecutionHistoryRepository;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Service
public class HistoryLogService {
    
    public static final String STATUS_SUCCESS = "S";
    public static final String STATUS_ERROR = "E";
    public static final String STATUS_RUNNING = "P";

    private final JobExecutionHistoryRepository repo;

    public HistoryLogService(JobExecutionHistoryRepository repo) {
        this.repo = repo;
    }

    public Long start(String jobCode) {
        JobExecutionHistory history = new JobExecutionHistory();
        history.setJobId(jobCode);
        history.setStartTime(LocalDateTime.now());
        history.setStatus(STATUS_RUNNING);
        return repo.save(history).getId();
    }

    public void success(Long id, String message) {
        repo.findById(id).ifPresent(history -> {
            history.setEndTime(LocalDateTime.now());
            history.setStatus(STATUS_SUCCESS);
            history.setMessage(message);
            repo.save(history);
        });
    }

    public void error(Long id, Throwable t) {
        repo.findById(id).ifPresent(history -> {
            history.setEndTime(LocalDateTime.now());
            history.setStatus(STATUS_ERROR);

            StringWriter sw = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));
            String detail = (t.getMessage() != null) ? t.getMessage() : t.toString() + "\n" + sw;
            history.setMessage(detail);
            repo.save(history);
        });
    }
}

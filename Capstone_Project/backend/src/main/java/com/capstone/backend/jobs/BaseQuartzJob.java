package com.capstone.backend.jobs;

import com.capstone.backend.service.HistoryLogService;
import com.capstone.backend.service.JobLogService;
import com.capstone.backend.repository.JobRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.time.ZoneId;

public abstract class BaseQuartzJob implements Job {

    @Autowired
    protected HistoryLogService historyLogService;

    @Autowired
    protected JobLogService jobLogService;

    @Autowired
    protected JobRepository jobRepository;

    @Override
    public final void execute(JobExecutionContext context) {
        String jobCode = context.getMergedJobDataMap().getString("jobCode");
        Long jobId = context.getMergedJobDataMap().getLong("jobId");

        jobLogService.markStart(jobId);
        Long historyId = historyLogService.start(jobCode);

        try {
            String msg = doWork(context);

            Date nextFireTime = context.getTrigger().getNextFireTime();
            if (nextFireTime != null) {
                jobRepository.findById(jobId).ifPresent(job -> {
                    job.setNextTriggerDate(nextFireTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    jobRepository.save(job);
                });
            }

            jobLogService.markSuccess(jobId);
            historyLogService.success(historyId, msg != null ? msg : "Completed successfully");
        } catch (Exception e) {
            jobLogService.markError(jobId);
            historyLogService.error(historyId, e);

            throw new RuntimeException(e);
        }
    }

    protected abstract String doWork(JobExecutionContext context) throws Exception;
}

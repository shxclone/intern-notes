package com.capstone.backend.service;

import com.capstone.backend.model.Job;
import com.capstone.backend.repository.JobRepository;
import com.capstone.backend.jobs.SimpleQuartzJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class JobExecutorService {

    private static final Logger logger = LoggerFactory.getLogger(JobExecutorService.class);
    
    private final SchedulerFactoryBean schedulerFactoryBean;
    private final JobRepository jobRepository;

    public JobExecutorService(SchedulerFactoryBean schedulerFactoryBean, JobRepository jobRepository) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.jobRepository = jobRepository;
    }

    @PostConstruct
    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<Job> jobs = jobRepository.findAll();

        for (Job job : jobs) {
            if ("Y".equalsIgnoreCase(job.getActive()) && job.getCronExpression() != null) {
                JobDetail jobDetail = JobBuilder.newJob(SimpleQuartzJob.class)
                        .withIdentity(job.getJobCode(), "job-group")
                        .usingJobData("jobId", job.getId())
                        .usingJobData("jobCode", job.getJobCode())
                        .build();

                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(job.getJobCode() + "_trigger", "job-group")
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                        .build();

                if (!scheduler.checkExists(jobDetail.getKey())) {
                    scheduler.scheduleJob(jobDetail, trigger);
                }

                logger.info("Scheduled job: {} with cron expression: {}", job.getJobCode(), job.getCronExpression());
            }
        }
        scheduler.start();
    }

    public void runJobNow(Long id) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));

        JobDetail jobDetail = JobBuilder.newJob(SimpleQuartzJob.class)
                .withIdentity(job.getJobCode() + "_manual", "manual-group")
                .usingJobData("jobId", job.getId())
                .usingJobData("jobCode", job.getJobCode())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getJobCode() + "_manual_trigger", "manual-group")
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        logger.info("Manually triggered job: {}", job.getJobCode());
    }

    public void rescheduleJob(Job job) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = new JobKey(job.getJobCode(), "DEFAULT"); // adjust group if you use one
        TriggerKey triggerKey = new TriggerKey(job.getJobCode() + "_trigger", "DEFAULT");

        // If the job doesn’t exist yet, create it first
        if (!scheduler.checkExists(jobKey)) {
            JobDetail jobDetail = JobBuilder.newJob(SimpleQuartzJob.class)
                    .withIdentity(jobKey)
                    .build();
            CronTrigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();
            scheduler.scheduleJob(jobDetail, newTrigger);
        } else {
            // Replace the existing trigger with the updated cron
            CronTrigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();
            scheduler.rescheduleJob(triggerKey, newTrigger);
        }
    }
}

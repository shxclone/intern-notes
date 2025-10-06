package com.capstone.backend.jobs;

import org.quartz.*;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SimpleQuartzJob extends BaseQuartzJob {

    private static final Logger logger = LoggerFactory.getLogger(SimpleQuartzJob.class);

    @Override
    protected String doWork(JobExecutionContext context) throws Exception {
        
        int processed = 0;

        // Simulate job
        logger.info("Executing job: {}", context.getJobDetail().getKey());
        Thread.sleep(10000); // Simulate work by sleeping for 10 seconds

        processed = 100; // Simulate processing 100 records

        return "Processed " + processed + " records.";
    }
}

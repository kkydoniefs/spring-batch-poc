package com.awls.delphi.drawmanagement.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job started: " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus().isUnsuccessful()) {
            System.out.println("Job failed with the following exceptions: " + jobExecution.getAllFailureExceptions());
        } else {
            System.out.println("Job completed successfully: " + jobExecution.getJobInstance().getJobName());
        }
    }
}


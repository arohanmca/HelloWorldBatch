package com.example.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HWJobExecutionListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before starting the Job JobName: " + jobExecution.getJobInstance().getJobName());
        System.out.println("Before starting the Job ExecutionContext: " + jobExecution.getExecutionContext().toString());
        jobExecution.getExecutionContext().put("my name", "Rohan");
        System.out.println("After starting the Job ExecutionContext: " + jobExecution.getExecutionContext().toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After starting the Job ExecutionContext: " + jobExecution.getExecutionContext().toString());
    }
}

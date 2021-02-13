package com.example.batch.config;

import com.example.batch.HelloWorldBatchApplication;
import com.example.batch.listener.HWJobExecutionListener;
import com.example.batch.listener.HWStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private HWJobExecutionListener hwJobExecutionListener;

    @Autowired
    private HWStepExecutionListener hwStepExecutionListener;


    @Bean
    public Step step1() {

        return steps.get("step1")
                .listener(hwStepExecutionListener)
                .tasklet(helloWorldTasklet())
                .build();

    }


    private Tasklet helloWorldTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("hello world");
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Job helloWorldJob() {
        return jobs.get("helloWorldJob")
                .listener(hwJobExecutionListener)
                .start(step1())
                .build();
    }
}

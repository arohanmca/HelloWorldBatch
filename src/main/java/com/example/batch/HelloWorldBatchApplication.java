package com.example.batch;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldBatchApplication {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldBatchApplication.class, args);
	}

	@Bean
	public Step step1() {
		
		return steps.get("step1")
				.tasklet(helloworldTasklet())
				.build();
		
	}

	private Tasklet helloworldTasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("hello world");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	@Bean
	public Job hellowWorldJob() {
		return jobs.get("helloWorldJob")
				.start(step1())
				.build();
	}
}

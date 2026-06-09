package com.awls.delphi.drawmanagement.config;

import com.awls.delphi.drawmanagement.batch.SimpleItemProcessor;
import com.awls.delphi.drawmanagement.batch.SimpleItemReader;
import com.awls.delphi.drawmanagement.batch.SimpleItemWriter;
import com.awls.delphi.drawmanagement.listener.CustomJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step step, CustomJobExecutionListener listener) {
        return new JobBuilder("job", jobRepository)
                .start(step)
                .listener(listener)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(3)
                .skip(Exception.class)
                .skipLimit(5)
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        return new SimpleItemReader();
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return new SimpleItemProcessor();
    }

    @Bean
    public ItemWriter<String> writer() {
        return new SimpleItemWriter();
    }
}


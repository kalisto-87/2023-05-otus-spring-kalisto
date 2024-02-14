package ru.otus.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final JobRepository jobRepository;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}

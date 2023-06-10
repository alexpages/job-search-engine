package com.java.jobsearchengine.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobConfig {

    @Bean
    CommandLineRunner commandLineRunner(JobRepository jobRepository){
        return args -> {
            Job job1 = new Job(
                    "Backend Developer",
                    "Spain",
                    "Meta",
                    "null",
                    "http....."
            );
            jobRepository.insert(List.of(job1));
        };
    }
}
